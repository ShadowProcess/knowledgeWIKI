package com.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DailyTest {

    @Test
    public void _0(){
        Object o = Optional.ofNullable(null).isPresent();
    }

    @Test
    public void _1(){
        boolean ss = StringUtils.equals("-1", "ss");
        System.out.println(ss);
    }

    @Test
    public void _2(){
        List<String> list = new ArrayList<>();

        boolean anyMatch = list.stream().anyMatch(it -> it.equals("1"));
        System.out.println(anyMatch);

        boolean noneMatch = list.stream().noneMatch(it -> it.equals("1"));
        System.out.println(noneMatch);

        boolean allMatch = list.stream().allMatch(it -> it.equals("1"));
        System.out.println(allMatch);
    }

}
