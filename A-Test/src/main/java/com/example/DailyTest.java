package com.example;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DailyTest {

    @Test
    public void _0() {
        Object o = Optional.ofNullable(null).isPresent();
    }

    @Test
    public void _1() {
        boolean ss = StringUtils.equals("-1", "ss");
        System.out.println(ss);
    }

    public static <T,E> void s(T s,E y){
    }

    @Test
    public void _0_(){
        s("ss","ff");
    }

}
