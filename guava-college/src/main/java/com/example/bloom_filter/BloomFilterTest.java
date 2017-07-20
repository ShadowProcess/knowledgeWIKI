package com.example.bloom_filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * 布隆过滤器
 */

public class BloomFilterTest {


    @Test
    public void _0(){
        //创建布隆过滤器
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                500,
                0.01
        );

        //向过滤器插入元素
        filter.put(1);
        filter.put(2);
        filter.put(3);

        //我们添加了3个元素，并且定义了最大元素数量为500，因此我们的过滤器将会产生非常准确的结果。我们使用mightContain()方法来测试
        boolean b = filter.mightContain(1);
        boolean b1 = filter.mightContain(2);
        boolean b2 = filter.mightContain(100);
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);

        //因为布隆过滤器是一种概率型数据结构，因此返回true表示元素有极大的概率存在。
        // 当返回false那么表示元素一定不存在。
        //当我们创建布隆过滤器时，尽可能提供准确的元素数量。否则将会产生较高的误报率
    }


    /**
     * 当我们创建布隆过滤器时，尽可能提供准确的元素数量。否则将会产生较高的误报率。
     * 下面的例子表示集合最多5个元素，这样在实际使用时就会产生很高的误报率。
     */
    @Test
    public void _1(){
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                5,
                0.01); //布隆过滤器误判率，默认为0.03

        IntStream.range(0, 100_000).forEach(filter::put);

        System.out.println(filter.mightContain(1));
    }


    /**
     * 优点:
     * 1.思路简单
     * 2.保证一致性
     * 3.性能强
     *
     * 缺点:
     * 1.代码复杂度增大
     * 2.需要另外维护一个集合来存放缓存的Key
     * 3.布隆过滤器不支持删值操作
     *
     * 以下是Redis伪代码
     */
//    String get(String key) {
//        //1.先从redis中取
//        String value = redis.get(key);
//        //2.不在redis中     
//        if (value == null) {
//            //3.不在布隆过滤器中,直接返回空
//            if(!bloomFilter.mightContain(key)){
//               return null;
//            } else{
//               //4.从数据库取值
//               value = db.get(key);
//               //5.把值存入redis中 
//               redis.set(key, value);
//            }    
//        }
//        return value；
//    }

}
