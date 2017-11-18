package com.example.reduce;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceDp {


    ///////////////////////////////////看完这个不明白Reduce，可以不用编程了，回家种地吧////////////////////////////////////////////////////

    /**
     * a=1 b=2
     * a=3 b=3
     * a=6 b=4
     * a=10 b=5
     * =====15
     */
    @Test
    public void test() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> reduce = integers.stream()
                .peek(it -> System.out.println(it))
                .reduce((a, b) -> {
                    System.out.println("a:::" + a);
                    System.out.println("b:::" + b);
                    return a + b;
                });
        System.out.println(reduce.get());
    }


    /**
     * a=0 b=1
     * a=1 b=2
     * a=3 b=3
     * a=6 b=4
     * a=10 b=5
     * =====15
     */
    @Test
    public void test111() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> reduce = Optional.ofNullable(integers.stream()
                .reduce(0, (a, b) -> {
                    System.out.println("a:::" + a);
                    System.out.println("b:::" + b);
                    return a + b;
                }));
        System.out.println(reduce.get()); //18
    }


    /**
     * 第三个参数，只有在并行时，才生效
     * , Integer::sum
     * 用于在并行时，合并多个单流的结果，所以叫组合器
     */
    @Test
    public void test1111() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce = integers.stream()
                .parallel() //todo 开并行
                .reduce(0, (a, b) -> {
                    System.out.println("a:::" + a);
                    System.out.println("b:::" + b);
                    return a + b;
                }, Integer::sum);
        System.out.println(reduce);
    }
    ///////////////////////////////////////////////////////////////////////////////////////







    /**
     * 当流并行执行时，Java运行时将流分割为多个子流。
     * 在这种情况下，我们需要使用一个函数来将子流的结果合并为一个结果。
     * 这是上面代码片段中的组合器的角色，它是Integer::sum方法引用。
     * <p>
     * 源码：
     * public static int sum(int a, int b) {
     * return a + b;
     * }
     * <p>
     * 参数1：
     */
    @Test
    public void test1() {
        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);
        int computedAges = ages.parallelStream() //todo 注意并行流
                .reduce(0, (a, b) -> {
                    System.out.println("a:::" + a);
                    System.out.println("b:::" + b);
                    return a + b;
                }, Integer::sum);
        System.out.println(computedAges);
    }

    /**
     * 标识：组合函数的标识值，累加器的初始值。
     * <p>
     * 累加器：一个关联的、不干扰的、无状态的函数，用于将额外的元素合并到结果中。
     * <p>
     * 组合器：用于组合两个值的关联、不干扰、无状态函数，必须与累加器函数兼容。
     * <p>
     * 第三个参数只有并行流中才会执行：
     */
    @Test
    public void testMultiReduce() {
        ArrayList<List<String>> strings = new ArrayList<>();
        strings.add(Arrays.asList("1", "2", "3", "4"));
        strings.add(Arrays.asList("2", "3", "4", "5"));
        strings.add(Arrays.asList("3", "4", "5", "6"));

        // 非并行流
        Integer reduce1 = strings.stream().flatMap(e -> e.stream()).reduce(0,
                (acc, e) -> acc + Integer.valueOf(e), (u, t) -> {
                    // 非并行流，不会执行第三个参数
                    System.out.println("u----:" + u);
                    // 这里的返回值并没有影响返回结果
                    return null;
                });
        System.out.println("reduce1:" + reduce1);

        // 并行流
        Integer reduce2 = strings.parallelStream().flatMap(e -> e.stream()).reduce(0,
                (acc, e) -> acc + Integer.valueOf(e), (u, t) -> {
                    // u，t分别为并行流每个子任务的结果
                    System.out.println("u----:" + u);
                    System.out.println("t----:" + t);
                    return u + t;
                });
        System.out.println("reduce2:" + reduce2);
    }


}
