package com.example.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 *
 * 缓存在很多场景下都是相当有用的。例如，计算或检索一个值的代价很高，并且对同样的输入需要不止一次获取值的时候，就应当考虑使用缓存。
 *
 * Guava Cache与ConcurrentMap很相似，但也不完全一样。最基本的区别是ConcurrentMap会一直保存所有添加的元素，直到显式地移除。相对地，Guava Cache为了限制内存占用，通常都设定为自动回收元素。在某些场景下，尽管LoadingCache 不回收元素，它也是很有用的，因为它会自动加载缓存。
 *
 * 通常来说，Guava Cache适用于：
 *
 * 你愿意消耗一些内存空间来提升速度。
 * 你预料到某些键会被查询一次以上。
 * 缓存中存放的数据总量不会超出内存容量。（Guava Cache是单个应用运行时的本地缓存。它不把数据存放到文件或外部服务器。如果这不符合你的需求，请尝试Memcached这类工具）
 *
 */

public class Test1 {

    @Test
    public void _0() throws ExecutionException {
        LoadingCache<String, Object> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(
                        new CacheLoader<String, Object>() {
                            @Override
                            public Object load(String key) throws Exception {
                                return new Object();
                            }
                        }
                );

        Object key = graphs.get("key");
        // 。。。
    }

}
