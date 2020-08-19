package com.example.stream.other;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ReduceDemo {


    @Test
    public void test0() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5);

        Optional<Integer> sum = numbers.stream()
                .reduce((left, right) -> {
                    System.out.println("left--" + left);
                    System.out.println("right--" + right);
                    return left + right;
                });

        System.out.println(sum.get());
        //left--1
        //right--2
        //left--3
        //right--3
        //left--6
        //right--5
        //11
    }


    /**
     * a: 代表第一个元素
     * b: 代表第二个元素
     */
    @Test
    public void test1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5);

        Integer sum = numbers.stream()
                .reduce(10, (left, right) -> {
                    System.out.println("left--" + left);
                    System.out.println("right--" + right);
                    return left + right;
                });

        System.out.println(sum);
        //left--10
        //right--1
        //left--11
        //right--2
        //left--13
        //right--3
        //left--16
        //right--5
        //21
    }

    @Test
    public void test2() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // 1*10 + 2*10 + 3*10
        Integer sum = numbers.stream()
                .reduce(10, (identity, val) -> {
                    System.out.println("identity--"+identity);
                    System.out.println("val--"+val);
                    return identity * val;
                }, (left, right) -> {
                    System.out.println("left--" + left);
                    System.out.println("right--" + right);
                    return left + right;
                });

        System.out.println(sum); //output 60
    }

    @Test
    public void u111(){
        System.out.println("abcd".substring(-1));
    }


    @Test
    public void ll(){
        String luckUser = "1234567891";
        System.out.println(luckUser.substring(0, 3) + "****" + luckUser.substring(6, 10));
    }

    /**
     * NullPointerException
     * null不能和基本类型进行比较
     *
     * 对于每一个Java程序员来说,null肯定是一个让人头痛的东西,连Java的发明者都承认这是一项巨大的设计失误,今天就来总结一下Java中关于null的知识.
     *
     * 1.null不属于任何类型,可以被转换成任何类型,但是用instanceof永远返回false.
     * 2.null永远不能和八大基本数据类型进行赋值或比较运算等,否则不是编译出错,就是运行出错.
     * 3.null可以和字符串进行运算.
     * 4.同种类型的null,比较都返回true,null==null也返回true.
     */
    @Test
    public void ee(){
        Long x = null;
        if (5 == x) {
            System.out.println("11");
        } else {
            System.out.println("22");
        }
    }

    @Test
    public void lop(){
        System.out.println(Objects.equals(null, 0L));
    }

}
