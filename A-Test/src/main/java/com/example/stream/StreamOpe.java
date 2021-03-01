package com.example.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOpe {


    /**
     * 串行或者并行的一个流中，如果在流式处理元素时，如果有一个元素不合法会怎样？
     *
     * 1.不做try,catch处理，那么不管是串行流，还是并行流，都将报错，程序终止
     * 2.做try,catch处理，那么不管是串行流还是并行流，都将正常进行
     */
    @Test
    public void _3Serial() {
        List<Integer> collect = Stream.of("1", "2", "3", "4", "pp")
                .peek(System.out::println) //串行流可以保证元素顺序
                .map(x -> {
                    Integer y = null;
                    try {
                        y = Integer.parseInt(x);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    return y;
                })
                .collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void _3Parallel() {
        List<Integer> collect = Stream.of("1", "2", "3", "4", "pp")
                .parallel()
                .peek(System.out::println) //并行流中无法保证元素顺序
                .map(x -> {
                    Integer y = null;
                    try {
                        y = Integer.parseInt(x);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    return y;
                })
                .collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void _2() {
        List<String> list = new ArrayList<>();

        boolean anyMatch = list.stream().anyMatch(it -> it.equals("1"));
        System.out.println(anyMatch); //TODO false

        boolean noneMatch = list.stream().noneMatch(it -> it.equals("1"));
        System.out.println(noneMatch);//TODO true

        boolean allMatch = list.stream().allMatch(it -> it.equals("1"));
        System.out.println(allMatch); //TODO true (特殊)
    }


    @Test
    public void _0() {
        List<Long> collect = Stream.of("1", "2", "3")
                .map(Integer::new)
                .map(Long::new)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    //流复用
    @Test
    public void _1() {
        Stream stream = Arrays.asList(1, 23, 45).stream();
        stream.forEach(
                System.out::println
        );

        //stream has already been operated upon or closed
        //流操作已经关闭
        stream.forEach(
                System.out::println
        );
        //java.lang.IllegalStateException: stream has already been operated upon or closed
    }
}
