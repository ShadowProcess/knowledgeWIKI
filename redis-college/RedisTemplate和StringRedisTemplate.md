###
两者的数据是不共通的；也就是说StringRedisTemplate只能管理StringRedisTemplate里面的数据，
RedisTemplate只能管理RedisTemplate中的数据。

其实他们两者之间的区别主要在于他们使用的序列化类:
 RedisTemplate使用的是JdkSerializationRedisSerializer   
 （存入数据会将数据先序列化成字节数组然后在存入Redis数据库。） 

 StringRedisTemplate使用的是StringRedisSerializer

使用时注意事项：
　　　当你的redis数据库里面本来存的是字符串数据或者你要存取的数据就是字符串类型数据的时候，
     那么你就使用StringRedisTemplate即可。
　　　但是如果你的数据是复杂的对象类型，而取出的时候又不想做任何的数据转换，
     直接从Redis里面取出一个对象，那么使用RedisTemplate是更好的选择。

RedisTemplate使用时常见问题：
　　　　redisTemplate 中存取数据都是字节数组。当redis中存入的数据是可读形式而非字节数组时，
      使用redisTemplate取值的时候会无法获取导出数据，获得的值为null。
      可以使用 StringRedisTemplate 试试。

###


# Jedis和RedisTemplate有何区别？

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
