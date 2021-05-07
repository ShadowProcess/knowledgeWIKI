package com.example.apigateway.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 指定缓存失效时间
     * 时间小于0时，不设置缓存时间，认为永久缓存
     *
     * @param key
     */
    public void expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key，不能为null
     * @return 时间单位s 0代表永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    // ============================String=============================	

    /**
     * string缓存获取
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * string存入
     *
     * @param key
     * @param value
     * @return
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存并设置过期时间
     *
     * @param key
     * @param value
     * @param time  过期时间(秒) <=0 永久缓存
     * @return
     */
    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /**
     * 缓存并设置过期时间
     *
     * @param key
     * @param value
     * @param time  过期时间(小时) <=0 永久缓存
     * @return
     */
    public void setValue(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.HOURS);
        } else {
            set(key, value);
        }
    }

    // ================================Map=================================

    /**
     * Hash get
     *
     * @param key  hash-key 不能为null
     * @param item item-key 不能为null
     * @return
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * hashKey新增或修改item=value数据
     *
     * @param key
     * @param item
     * @param value
     */
    public void hset(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * hashKey新增或修改item=value数据 并设置过期时间（如果item已经从，则更新过期时间）
     *
     * @param key
     * @param item
     * @param value
     * @param time
     */
    public void hset(String key, String item, Object value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        expire(key, time);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hgetMore(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Hash缓存
     *
     * @param key
     * @param map 对应多个键值
     */
    public void hsetMore(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * Hash缓存 并设置过期时间
     *
     * @param key
     * @param map  对应多个键值
     * @param time 过期时间，单位秒
     */
    public void hsetMore(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        expire(key, time);
    }

    /**
     * 删除hashKey中的item
     *
     * @param key  不能为null
     * @param item 可为多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hashKey中的item是否存在
     *
     * @param key
     * @param item
     * @return true存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    // ============================set=============================   

    /**
     * 根据key获取Set中的所有值
     *
     * @param key
     * @return
     */
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 查询set中是否存在value
     *
     * @param key
     * @param value
     * @return true存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set
     *
     * @param key
     * @param values 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    /**
     * 获取set的长度
     *
     * @param key
     * @return
     */
    public long sGetLen(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值value
     *
     * @param key
     * @param values 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 移除hash的值
     *
     * @param key
     * @return
     */
    public Boolean setRemove(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 移除key-value的值
     *
     * @param set key的集合
     * @return
     */
    public Long setRemove(Set<String> set) {
        return redisTemplate.delete(set);
    }


    ;
    // ===============================list=================================

    /**
     * 获取list内容 （0到-1代表所有值）
     *
     * @param key
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list的长度
     *
     * @param key
     * @return
     */
    public long lGetLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过索引 获取list的值（0第一个值，1 第二个值，-1，最后一个值，-2倒数第二个值）
     *
     * @param key
     * @param index 索引位置
     * @return
     */
    public Object lGetIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 将list放入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public long lSet(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @param key
     * @param value
     * @param time  时间
     * @return
     */
    public long lSet(String key, Object value, long time) {
        long count = redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return count;
    }

    /**
     * list缓存  添加list到缓存list中
     *
     * @param key
     * @param value
     * @return
     */
    public long lSetAll(String key, List<Object> value) {
        return redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * list缓存 添加list到缓存list中 并设置过期时间
     *
     * @param key
     * @param value
     * @param time  过期时间
     * @return
     */
    public long lSetAll(String key, List<Object> value, long time) {
        long count = redisTemplate.opsForList().rightPushAll(key, value);
        expire(key, time);
        return count;

    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public void lUpdateIndex(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除个数
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 获取所有匹配正则的键值
     *
     * @param
     * @return
     */
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
