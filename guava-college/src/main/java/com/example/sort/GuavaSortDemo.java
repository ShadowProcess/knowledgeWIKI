package com.example.sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GuavaSortDemo {

    //guava的 Ordering自然排序
    @Test
    public void _9() {
        List<Integer> integers = Arrays.asList(1, 5, 6, 8, 9, 0);
        integers.sort(Ordering.natural());
        System.out.println(integers);
    }

    //按对象的字符串形式做字典排序
    @Test
    public void _10() {
        ArrayList<String> strings = Lists.newArrayList("wang", "test", "a");
        strings.sort(Ordering.usingToString());
        System.out.println(strings);
        System.out.println("-------------------");
    }

    //先按照自然排序，再反转
    @Test
    public void _11() {
        List<Integer> integers = Arrays.asList(1, 5, 8, 7, 9);
        integers.sort(Ordering.natural().reverse());
        System.out.println(integers);
    }

    /**
     * JDK原生  排序中出现，空指针会异常的！
     */
    @Test//(expected = NullPointerException.class)
    public void testJDKOrderIssue() {
        List<Integer> list = Arrays.asList(1, 5, null, 3, 8, 2);
        System.out.println("排序前" + list.toString());
        Collections.sort(list); // 出现异常...
    }

    @Test
    public void _12() {
        List<Integer> integers = Arrays.asList(1, 5, null, 6, 8, 2);
        integers.sort(Ordering.natural().nullsFirst());
        System.out.println(integers);
    }

    //使用两个字段进行排序
    //Ordering<Worker> orderManyWorker = Ordering.from(new WorkerIdComparator()).compound(new WorkerNameComparator()).reverse();
    //Collections.sort(workers, orderManyWorker);
    //log.info("多参数：" + workers.toString());

    /**
     * 判断是否是按照指定排序，进行排序的
     * Guava的排序器实现有若干操纵集合或元素值的方法
     * isOrdered
     */
    @Test
    public void _13(){
        List<Integer> integers = Arrays.asList(1, 0, 5, 34, 8);
        Collections.sort(integers);

        boolean ordered = Ordering.natural().isOrdered(integers); ////是否为按照这样的顺序排好序的！自然的排序
        System.out.println("是否是按照自然排序的："+ordered);
    }

    /**
     * 返回元素中的最大值，同理最小的
     */
    @Test
    public void _14(){
        List<Integer> integers = Arrays.asList(1, 5, 8, 9, 2);
        System.out.println("最大的元素:"+Ordering.natural().max(integers).toString());
        System.out.println("最小的元素:"+Ordering.natural().min(integers).toString());
    }

    /**
     * 获取集合中，最大的n个元素
     */
    @Test
    public void _15(){
        List<Integer> integers = Arrays.asList(1, 5, 8, 9, 2);
        List<Integer> integers1 = Ordering.natural().greatestOf(integers, 3);

        System.out.println(integers1); //[9, 8, 5]
    }


}
