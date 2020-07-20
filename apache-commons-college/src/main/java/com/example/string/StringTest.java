package com.example.string;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;


public class StringTest {


    @Test
    public void _0(){

        boolean blank = StringUtils.isBlank("  ");
        System.out.println(blank);
    }
}
