package com.example.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOpe {

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
    public void _0(){
        List<Long> collect = Arrays.asList("1", "2", "3")
                .stream()
                .map(Integer::new)
                .map(Long::new)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    //流复用
    @Test
    public void _1(){
        Stream stream = Arrays.asList(1,23,45).stream();
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
