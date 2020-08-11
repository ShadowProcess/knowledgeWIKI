package com.example.redisdemo.jedis;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

//# 集群版
public class JedisClusterDemo {
    public static void main(String[] args) {
        //访问Redis集群
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        nodes.add(new HostAndPort("192.168.25.131", 6661));
        nodes.add(new HostAndPort("192.168.25.131", 6662));
        nodes.add(new HostAndPort("192.168.25.131", 6663));
        nodes.add(new HostAndPort("192.168.25.131", 6664));
        nodes.add(new HostAndPort("192.168.25.131", 6665));
        nodes.add(new HostAndPort("192.168.25.131", 6666));

        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("name", "alex");
        String value = cluster.get("name");
        System.out.println(value);
    }
}

//# 单机版
class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println("foo is:" + value);
    }
}


//# 连接池方式
class JedisPoolTest {
    static JedisPoolConfig config;
    static JedisPool jedisPool;

    static {
        // 初始化连接池配置对象
        config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal(30);
        config.setMaxWaitMillis(3 * 1000);
        // 实例化连接池
        jedisPool = new JedisPool(config, "localhost", 6379);
    }

    public static void main(String[] args) {
        // 从连接池获取Jedis连接
        Jedis jedisConn = jedisPool.getResource();
        jedisConn.del("cities");
        jedisConn.lpush("cities", "北京");
        jedisConn.lpush("cities", "上海");
        jedisConn.lpush("cities", "广州");
        System.out.println(jedisConn.lrange("cities", 0, -1));
        // 释放连接
        close(jedisConn, jedisPool);
    }

    private static void close(Jedis jedisConn, JedisPool pool) {
        if (jedisConn != null && pool != null) {
            jedisConn.close();
            pool.close();
        }
        if (pool != null) {
            jedisPool.destroy();
        }
    }
}
