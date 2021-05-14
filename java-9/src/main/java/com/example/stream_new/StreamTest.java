//package com.example.stream_new;
//
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Stream;
//
////jdk 9 中针对于Stream 新添加了4个方法
//public class StreamTest {
//
//    //1.takeWhile()
//    //用于从Stream中获取一部分数据，接收一个Predicate来进行选择，在有序的Stream中，takeWhile返回从头开始的尽量多的元素
//    @Test
//    public void test1() {
//        List<Integer> list = Arrays.asList(45, 56, 33, 77, 44, 98, 76, 78, 33);
//        Stream<Integer> stream = list.stream();
//
//        /**
//         * takeWhile意思是：
//         * 从头开始，尽可能多的去取，一旦发现有一个不满足的，后面的都不要了
//         */
//        stream.takeWhile(x -> x < 70).forEach(System.out::println); //45,56,33
//
//
//        /**
//         * takeWhile意思是：
//         * 从头开始，尽可能多的去取，一旦发现有一个不满足的，后面的都不要了
//         */
//        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//        list1.stream().takeWhile(x -> x < 5).forEach(System.out::println); //1,2,3,4
//    }
//
//
//    //2.dropWhile():与 takeWhile() 整好相反
//    @Test
//    public void test2() {
//        List<Integer> list = Arrays.asList(45, 56, 33, 77, 44, 98, 76, 78, 33);
//        Stream<Integer> stream = list.stream();
//        /**
//         * dropWhile意思是：
//         * 从头开始找，找到第一个不符合条件的，那么这个不符合条件的元素和后边的都要
//         */
//        stream.dropWhile(x -> x < 70).forEach(System.out::println); //77,44,98,76,78,33
//
//        /**
//         * dropWhile意思是：
//         * 从头开始找，找到第一个不符合条件的，那么这个不符合条件的元素和后边的都要
//         */
//        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
//        list1.stream().dropWhile(x -> x < 5).forEach(System.out::println);//5,6,7,8
//    }
//
//
//    //3.ofNullable(T t): t可以为null
//    @Test
//    public void test3() {
//        //Stream如果有多个元素，那么允许其中一个为null
//        Stream<Integer> stream1 = Stream.of(1, 2, 3, null);
//        stream1.forEach(System.out::println);
//
//
//        //Stream如果只有单个元素，此元素不能为null.否则报NullPointerException
//        //Stream<Object> stream2 = Stream.of(null);
//
//
//        //jdk 9 :新增ofNullable(T t):
//        Stream<String> stream3 = Stream.ofNullable("Tom");
//        System.out.println(stream3.count());//1
//
//        Stream<String> stream4 = Stream.ofNullable(null);
//        System.out.println(stream4.count());//0
//    }
//
//
//    //4.iterator()重载的方法
//    @Test
//    public void test4() {
//        /**
//         * Stream的实例化方式有几种?
//         * ① 通过集合的stream()
//         * ② 通过数组工具类：Arrays
//         * ③ Stream中静态方法：of()
//         * ④ iterator() / generate()
//         */
//
//        //获取一个流; 如果不用limit限制，这个流会一直进行下去
//        Stream.iterate(0, x -> x + 1)
//                .limit(10)
//                .forEach(System.out::println);
//
//
//
//        //jdk9的重载方法; 第二个参数Predicate，对流进行限制
//        Stream.iterate(0, x -> x < 10, x -> x + 1)
//                .forEach(System.out::println);
//    }
//}
