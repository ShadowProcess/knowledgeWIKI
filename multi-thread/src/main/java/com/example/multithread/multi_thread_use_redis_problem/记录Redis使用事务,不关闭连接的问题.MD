Redis(Use Jedis)
本次任务使用到Redis事务，使用的spring-data-redis来开发,配置好连接池，进入使用，似乎一切正常
配置了两块Redis，一个专门做读，一个专门做写，配置的XML文件如下，这是一个专门做写的Redis配置
```xml
<bean id="poolConfig" class="redis.clients.jedis.JedisPoolConfig">
		<property name="maxIdle" value="${redis.maxIdle}" />
		<property name="maxWaitMillis" value="${redis.maxWaitMillis}" />
		<property name="maxTotal" value="${redis.maxTotal}"></property>
		<property name="testOnBorrow" value="${redis.testOnBorrow}" />
		<property name="minIdle" value="${redis.minIdle}"></property>
		<property name="timeBetweenEvictionRunsMillis" value="60000"></property>
		<property name="minEvictableIdleTimeMillis" value="1800000"></property>
		<property name="numTestsPerEvictionRun" value="3"></property>
	</bean>
	<bean id="connectionFactory"
		class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory"
		p:host-name="${redis.host}" p:port="${redis.port}" p:password="${redis.pass}"
		p:pool-config-ref="poolConfig">
	</bean>
 
	<!-- 配置redisTemplate -->
	<bean id="redisTemplate" class="org.springframework.data.redis.core.StringRedisTemplate">
		<property name="connectionFactory" ref="connectionFactory" />
		<property name="keySerializer">
			<bean
				class="org.springframework.data.redis.serializer.StringRedisSerializer" />
		</property>
		<!-- 采用json序列化 -->
		<property name="valueSerializer">
			<bean
				class="org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer">
			</bean>
		</property>
		<!-- 开启Redis事务支持 -->
		<property name="enableTransactionSupport" value="true" /> 
</bean>
```

配置好以后编码使用，发现一切正常。但是有一次一个现象引起了注意，同事在编码的时候，
出现了“无法从连接池获取redis连接“的错误（Could not get a resource connection from the pool）。
于是调用端口查看，发现连接池被占满了，redisTemplate似乎没有释放掉连接，于是进一步查看，发现只有启用了事务的连接没有被释放，
记得以前听人说过 exec和discard会自动关闭连接，于是往配置上去寻找问题，多番寻找和尝试无果，
后来又有人说据说用@transactional或spring的事务AOP可以控制和传播事务，并控制连接，但是我们的需求是自己做，
所以只好去看redisTemplate源码。从exec处入手，很快找到了最终执行的源码，RedisTemplate的所有操作执行，
大部分都是通过这个方法来回调action执行的，大家可以感受感受：
```
	public <T> T execute(RedisCallback<T> action, boolean exposeConnection, boolean pipeline) {
		Assert.isTrue(initialized, "template not initialized; call afterPropertiesSet() before using it");
		Assert.notNull(action, "Callback object must not be null");
 
		RedisConnectionFactory factory = getConnectionFactory();
		RedisConnection conn = null;
		try {
 
			if (enableTransactionSupport) {
				// only bind resources in case of potential transaction synchronization
				conn = RedisConnectionUtils.bindConnection(factory, enableTransactionSupport);
			} else {
				conn = RedisConnectionUtils.getConnection(factory);
			}
 
			boolean existingConnection = TransactionSynchronizationManager.hasResource(factory);
 
			RedisConnection connToUse = preProcessConnection(conn, existingConnection);
 
			boolean pipelineStatus = connToUse.isPipelined();
			if (pipeline && !pipelineStatus) {
				connToUse.openPipeline();
			}
 
			RedisConnection connToExpose = (exposeConnection ? connToUse : createRedisConnectionProxy(connToUse));
			T result = action.doInRedis(connToExpose);
 
			// close pipeline
			if (pipeline && !pipelineStatus) {
				connToUse.closePipeline();
			}
 
			// TODO: any other connection processing?
			return postProcessResult(result, connToUse, existingConnection);
		} finally {
            //这里是控制是否释放或归还连接的代码 (如果没有开启事务才会(归还/释放)连接)
			if (!enableTransactionSupport) {
				RedisConnectionUtils.releaseConnection(conn, factory);
			}
		}
	}
```
关键代码是这一句，RedisConnectionUtils.releaseConnection里是归还/释放连接的代码，很显然，
只要你设置了enableTransactionSupport为true，或用mulit开启了事务，调用redisTemplate的回调函数时是永远不会归还/释放连接。
也不要尝试设置enableTransactionSupport为false去释放连接，因为他只会获取一个新的连接然后关闭，代码是这句：
```
if (enableTransactionSupport) {
	conn = RedisConnectionUtils.bindConnection(factory, enableTransactionSupport);
} else {
    //这里会获取一个新的连接
	conn = RedisConnectionUtils.getConnection(factory);
}
```

但知道连接是怎么处理的就好办了，我们手动释放即可，好在redisTemplate提供了获取getFactory的方法，
但是还需要获取到当前线程的connection，这个不难，追踪RedisConnectionUtils.bindConnection(factory, enableTransactionSupport);
可以发现，这句话就是绑定/返回当前线程的connection的，如果当前线程还不存在connection，则创建一个。于是以下代码就可以做到返回连接给连接池。

```
redisTemplate.exec();	
// 获取当前线程绑定的连接
RedisConnection conn = RedisConnectionUtils.bindConnection(redisTemplate.getConnectionFactory(), true);
//返还给连接池
conn.close();
```

但是很快又发现另外一个问题，尝试大量访问的时候会报出这个异常：
Could not release connection to pool 
很明显是无法将连接返还给连接池，一番追踪后发现，第一次是永远不会报这个错的，只在访问达到一定次数的情况下才会比较频繁的出现，
这个不难理解，应该与会话缓存。显然是我们归还过一次连接池，第二次归还时，连接池抛出了错误，也就是说，被返还的连接还绑定在会话缓存上，
于是去看底层是如何bindConnection的，跟踪到这段代码。
```
public static RedisConnection doGetConnection(RedisConnectionFactory factory, boolean allowCreate, boolean bind,
			boolean enableTransactionSupport) {
 
		Assert.notNull(factory, "No RedisConnectionFactory specified");
 
		RedisConnectionHolder connHolder = (RedisConnectionHolder) TransactionSynchronizationManager.getResource(factory);
 
		if (connHolder != null) {
			if (enableTransactionSupport) {
				potentiallyRegisterTransactionSynchronisation(connHolder, factory);
			}
			return connHolder.getConnection();
		}
 
		if (!allowCreate) {
			throw new IllegalArgumentException("No connection found and allowCreate = false");
		}
 
		if (log.isDebugEnabled()) {
			log.debug("Opening RedisConnection");
		}
 
		RedisConnection conn = factory.getConnection();
 
		if (bind) {
 
			RedisConnection connectionToBind = conn;
			if (enableTransactionSupport && isActualNonReadonlyTransactionActive()) {
				connectionToBind = createConnectionProxy(conn, factory);
			}
 
			connHolder = new RedisConnectionHolder(connectionToBind);

            //这句就是执行了将连接绑定到线程的代码
			TransactionSynchronizationManager.bindResource(factory, connHolder);
			if (enableTransactionSupport) {
				potentiallyRegisterTransactionSynchronisation(connHolder, factory);
			}
 
			return connHolder.getConnection();
		}
		return conn;
	}
```

注意看我的注释。内部是通过TransactionSynchronizationManager.bindResource的，这下就好处理了，
TransactionSynchronizationManager里还提供了一个unbindResource的方法，调用方法是这样的：
TransactionSynchronizationManager.unbindResource(redisTemplate.getConnectionFactory());
于是修改代码为以下：
```
redisTemplate.exec();	
//步骤一：获取当前线程绑定的连接
RedisConnection conn = RedisConnectionUtils.bindConnection(redisTemplate.getConnectionFactory(), true);
//步骤二：返还给连接池
conn.close();
//步骤三：解绑线程连接
TransactionSynchronizationManager.unbindResource(redisTemplate.getConnectionFactory());
```
再次测试，没问题，再没有任何错误，连接数与响应速度也非常快。最后
修改代码，通过一个AOP来进行处理。

>在重新看代码的时候，发现了一个更简单的处理。
>在这个方法里已经做了上面的事情，调用这个解绑的方式，更简洁。
>修改代码
```
redisTemplate.exec();
RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
```
两句就可以了。



#总结:
如果采用spring的事务管理的话，spring的事务管理会处理好链接的问题。
我们因为没有使用spring的事务管理，所以才需要自己编码去解决.

