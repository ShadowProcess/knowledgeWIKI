# Jedis和RedisTemplate有何区别？

```
# 单机版
public class JedisTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println("foo is:"+value);
	}
}

# 连接池方式
public class JedisPoolTest {
	static JedisPoolConfig config;
	static JedisPool jedisPool;
	static {
		// 初始化连接池配置对象
		config = new JedisPoolConfig();
		config.setMaxIdle(10);
		config.setMaxTotal(30);
		config.setMaxWaitMillis(3*1000);
		// 实例化连接池
		jedisPool=new JedisPool(config, "localhost", 6379);
	}
	
	public static void main(String[] args) {
		// 从连接池获取Jedis连接
		Jedis jedisConn = jedisPool.getResource();
		jedisConn.del("cities"); 
		jedisConn.lpush("cities","北京"); 
		jedisConn.lpush("cities","上海"); 
		jedisConn.lpush("cities","广州"); 
		System.out.println(jedisConn.lrange("cities",0,-1)); 
		// 释放连接
		close(jedisConn, jedisPool);
	}
	
	private static void close(Jedis jedisConn,JedisPool pool){
		if(jedisConn!=null && pool!=null){
			pool.returnResource(jedisConn);
		}
		if(pool!=null){
			jedisPool.destroy();
		}
	}
}
```
Jedis是Redis官方推荐的面向Java的操作Redis的客户端，
而RedisTemplate是SpringDataRedis中对JedisApi的高度封装。
SpringDataRedis相对于Jedis来说可以方便地更换Redis的Java客户端，
比Jedis多了自动管理连接池的特性，方便与其他Spring框架进行搭配使用 
例如：SpringCache


# RedisTemplate和StringRedisTemplate的区别：

两者的关系是StringRedisTemplate继承RedisTemplate。
两者的数据是不共通的；也就是说StringRedisTemplate只能管理StringRedisTemplate里面的数据，
RedisTemplate只能管理RedisTemplate中的数据。

SDR默认采用的序列化策略有两种，一种是String的序列化策略，一种是JDK的序列化策略。
StringRedisTemplate默认采用的是String的序列化策略，保存的key和value都是采用此策略序列化保存的。
RedisTemplate默认采用的是JDK的序列化策略，保存的key和value都是采用此策略序列化保存的。

RedisTemplate使用的序列类在在操作数据的时候，比如说存入数据会将数据先序列化成字节数组然后在存入Redis数据库，
这个时候打开Redis查看的时候，你会看到你的数据不是以可读的形式展现的，而是以字节数组显示

当然从Redis获取数据的时候也会默认将数据当做字节数组转化，这样就会导致一个问题，
当需要获取的数据不是以字节数组存在redis当中而是正常的可读的字符串的时候

总结：
当你的redis数据库里面本来存的是字符串数据或者你要存取的数据就是字符串类型数据的时候，
那么你就使用StringRedisTemplate即可，但是如果你的数据是复杂的对象类型，而取出的时候又不想做任何的数据转换，
直接从Redis里面取出一个对象，那么使用RedisTemplate是更好的选择。

