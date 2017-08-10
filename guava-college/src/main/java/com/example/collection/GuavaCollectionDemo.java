package com.example.collection;

import com.google.common.base.Joiner;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.*;

public class GuavaCollectionDemo {

    /**
     * 集合工具[静态工厂方法]
     */
    @Test//(timeout = 5000,expected = Exception.class)
    public void test1() {
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();
        Map<String,String> map1 = Maps.newLinkedHashMap(); //linkedHashMap和treeMap是有序的

        //通过为工厂方法命名（Effective Java第一条），我们可以提高集合初始化大小的可读性：
        List<String> list1 = Lists.newArrayListWithCapacity(100);
        List<String> list2 = Lists.newArrayListWithExpectedSize(100);
        Set<String> set1 = Sets.newHashSetWithExpectedSize(100);

        //注意：Guava引入的新集合类型没有暴露原始构造器，也没有在工具类中提供初始化方法。而是直接在集合类中提供了静态工厂方法，例如：
        Multiset<String> multiset = HashMultiset.create();
    }


    @Test
    public void test5() {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5);
        Set<Integer> set2 = Sets.newHashSet(11, 21, 3, 41, 51);
        System.out.println("求交集");
        Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);
        for (Integer integer : intersection) {
            System.out.println(integer);
        }

        System.out.println("求差集[以第一个参数为主]");
        Sets.SetView<Integer> difference = Sets.difference(set1, set2);
        for (Integer integer : difference) {
            System.out.println(integer);
        }

        System.out.println("求并集");
        Sets.SetView<Integer> union = Sets.union(set1, set2);
        for (Integer integer : union) {
            System.out.println(integer);
        }
    }


    //guava不可变集合
    @Test
    public void _2() {
        //不可变Set
        ImmutableSet<String> of = ImmutableSet.of("1", "2", "3");
        for (String s : of) {
            System.out.println(s);
        }
        System.out.println(of.contains("1"));
        //不可变Map
        ImmutableMap<String, Integer> of1 = ImmutableMap.of("a", 1, "b", 2);
    }


    //copyOf
    @Test
    public void _0() {
        ImmutableSet<String> set = ImmutableSet.of("a", "b", "c");

        //拷贝一直list拷贝一个set
        ImmutableList<String> defensive = ImmutableList.copyOf(set);
        //在这段代码中，ImmutableList.copyOf(foobar)会智能地直接返回set.asList(),它是一个ImmutableSet的常量时间复杂度的List视图。
    }

    //jdk不可变集合
    @Test
    public void _3() {
        //返回值objects为不可变集合
        List<Object> objects = Collections.unmodifiableList(new ArrayList<>());
        /**
         * 创建对象的不可变拷贝是一项很好的防御性编程技巧。Guava为所有JDK标准集合类型和Guava新集合类型都提供了简单易用的不可变版本。
         *  JDK也提供了Collections.unmodifiableXXX方法把集合包装为不可变形式，但我们认为不够好：
         *
         * 笨重而且累赘：不能舒适地用在所有想做防御性拷贝的场景；
         * 不安全：要保证没人通过原集合的引用进行修改，返回的集合才是事实上不可变的；
         * 低效：包装过的集合仍然保有可变集合的开销，比如并发修改的检查、散列表的额外空间，等等。
         */
    }




    @Test
    public void _4() {
        List<Integer> countDown = Ints.asList(1, 2, 3, 4, 5);
        List<Integer> reverse = Lists.reverse(countDown);
    }

    @Test
    public void _5() {
        Set<String> words = ImmutableSet.of("1", "2", "3", "4", "5");
        Set<String> primes = ImmutableSet.of("q", "w", "e", "1");
        Sets.SetView<String> intersection = Sets.intersection(words, primes);
        intersection.immutableCopy(); //可以使用交集，但是不可变拷贝读取效率更高
    }


    //求笛卡尔积
    @Test
    public void _6() {
        Set<String> animals = ImmutableSet.of("g", "s");
        Set<String> fruits = ImmutableSet.of("a", "b", "v");
        Set<List<String>> lists = Sets.cartesianProduct(animals, fruits);
        //lists.forEach(it -> it.forEach(it1 -> System.out.println(it1)));
        for (List<String> list : lists) {
            Joiner joiner = Joiner.on(",").skipNulls();
            System.out.println("{" + joiner.join(list) + "}");
        }
        //{g,a}
        //{g,b}
        //{g,v}
        //{s,a}
        //{s,b}
        //{s,v}
    }

    //求集合的子集
    @Test
    public void _7() {
        ImmutableSet<String> of = ImmutableSet.of("a", "b");
        Set<Set<String>> sets = Sets.powerSet(of);
        for (Set<String> set : sets) {
            Joiner j = Joiner.on(",").skipNulls();
            System.out.println("{" + j.join(set) + "}");
        }
//        {}
//        {a}
//        {b}
//        {a,b}
    }


    //集合串联
    @Test
    public void _8() {

        Iterable<Integer> concat = Iterables.concat(
                Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6));

        Integer last = Iterables.getLast(concat);
        System.out.println(last);


        //这里会出错，因为不是单元素集合
        Integer onlyElement = Iterables.getOnlyElement(concat);
        System.out.println(onlyElement);
    }


    @Test
    public void _9(){
        //往集合存储3个a
        Multiset<String> s = HashMultiset.create();
        s.add("a",3);
        for (String s1 : s) {
            System.out.println(s1);
        }
    }
}
