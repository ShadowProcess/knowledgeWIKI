package com.example.joiner;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Arrays;

public class JoinerDemo {

    //字符串拼接
    @Test
    public void _0() {
        Joiner j = Joiner.on(";").skipNulls();
        String join = j.join("haa", null, "22", "wg");
        System.out.println(join);
    }

    //集合中字符串拼接
    public void _1() {
        Joiner j = Joiner.on(",").skipNulls();
        String s = j.join(Arrays.asList("1", "2", "6", "9"));
        System.out.println(s);
    }

    //字符串拆分器
    @Test
    public void _2() {
        Iterable<String> split = Splitter.on(",").trimResults().omitEmptyStrings().split("1,,3,4,56,,");
        System.out.println(split); //[1, 3, 4, 56]
    }


    //移除单个字符
    @Test
    public void _3() {
        //移除一个字符
        String s = CharMatcher.is('c')
                .removeFrom("ssscontrol");
        System.out.println(s);
        System.out.println("--------------------");

        //不匹配c的全部移除
        String s1 = CharMatcher.isNot('c')
                .removeFrom("ssscontrol");
        System.out.println(s1);
    }

    //移除指定字符串
    @Test
    public void _4() {
        String s = CharMatcher.anyOf("ss")
                .removeFrom("ss123445");
        System.out.println(s);
    }

    @Test
    public void _5() {
        //所有的都匹配
        boolean b = CharMatcher.is('a').matchesAllOf("aacc"); //false
        System.out.println(b);

        //任意一个匹配
        boolean b1 = CharMatcher.is('a').matchesAnyOf("aacc"); //true
        System.out.println(b1);

        //所有都不匹配
        boolean b2 = CharMatcher.is('a').matchesNoneOf("cccc");
        System.out.println(b2);
    }


    @Test
    public void _6() {
        //保留你指定的字符
        String s = CharMatcher.is('a').retainFrom("aaabbccc");
        System.out.println(s);//aaa

        //移除你指定的字符
        String s1 = CharMatcher.is('a').removeFrom("aaabbccc");
        System.out.println(s1);//bbccc

        //去掉字符串中 你指定的字符
        String s2 = CharMatcher.anyOf("ab").trimFrom("abacatba");
        System.out.println(s2);//cat
    }
}
