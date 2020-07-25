package com.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

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

}
