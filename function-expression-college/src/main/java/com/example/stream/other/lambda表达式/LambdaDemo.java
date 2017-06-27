package com.example.stream.other.lambda表达式;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class LambdaDemo {


    @Test
    public void test1() {

    }


    @Test
    public void test2() {
        // java8以前
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("启动");
            }
        }).start();

        // java8
        new Thread(() -> {
            System.out.println("lambda");
        }).start();
    }


    @Test
    public void test3() {
        JButton show = new JButton("show");

        //1.8之前
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("event");
            }
        });

        //java 8
        show.addActionListener((e) -> {
            System.out.println("lambda");
        });
    }

    @Test
    public void test4() {
        List<String> features = Arrays.asList("1", "2", "3", "4");
        for (String feature : features) {
            System.out.println(feature);
        }

        //java8
        features.forEach(n -> System.out.println(n));

        //// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        //// 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }


    @Test
    public void test5() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        //filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        //filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        //filter(languages, (str) -> str.length() > 4);
    }

    public static void filter(List<String> names, Predicate predicate) {
        for (String name : names) {
            if (predicate.test(name)) {
                System.out.println(name + "");
            }
        }
    }

    // 更好的办法
    public static void filter2(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }


    @Test
    public void test6() {
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }

        // 使用lambda表达式
        //List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        //costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
    }

    @Test
    public void test7() {
        String[] players = {"zhansgan", "lisi", "wangwu", "zhaoliu", "wangmazi"};

        //1.使用匿名内部类排序
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        //2.使用lambda表达式
        Arrays.sort(players, (String s1, String s2) -> s1.compareTo(s2));

        //3.使用匿名内部类根据name的length排序
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.length() - o2.length()); //从小到大排序
            }
        });

        //4.使用lambda，根据最后一个字母
        Arrays.sort(players, (String s1, String s2) -> (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1)));
    }


    /**
     * 二、方法引用
     * 1、基本介绍
     * 首先注意：方法引用，不是方法调用！方法引用，不是方法调用！方法引用，不是方法调用！
     * <p>
     * 函数式接口的实例可以通过 lambda 表达式、 方法引用、构造方法引用来创建。方法引用是 lambda 表达式的语法糖，任何用方法引用的地方都可由lambda表达式替换，
     * <p>
     * 但是并不是所有的lambda表达式都可以用方法引用来替换。
     * <p>
     * 举例:
     * 这就是一个打印集合所有元素的例子，value -> System.out.println(value) 是一个Consumer函数式接口， 这个函数式接口可以通过方法引用来替换。
     */

    @Test
    public void test8() {
        List<String> strings = Arrays.asList("q", "w", "e");
        strings.forEach(value -> System.out.println(value));
    }


    /**
     * 2、分类
     * 类别	使用形式
     * 静态方法引用	    类名 :: 静态方法名
     * 实例方法引用	    对象名(引用名) :: 实例方法名
     * 类方法引用	        类名 :: 实例方法名
     * 构造方法引用	    类名 :: new
     */
    public void test9() {
        List<String> appleList = Arrays.asList("qwe", "apple2", "apple32321", "apple42323123");

        //lambda 表达式形式
        //appleList.sort((Apple a1, Apple a2) -> {
        //    return new Double(a1.getWeight() - a2.getWeight()).intValue();
        //});

        //静态方法引用形式（可以看出引用方法比上面的更加简单
        appleList.sort((o1, o2) -> {
            return o1.length() - o2.length();
        });
    }


}
