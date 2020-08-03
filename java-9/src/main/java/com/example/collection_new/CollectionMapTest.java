package com.example.collection_new;

import org.junit.Test;

import java.util.*;

/**
 * 创建只读集合
 */

public class CollectionMapTest {

    //jdk 8 以及之前：创建一个只读特点的集合
    @Test
    public void test1(){
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Lilei");
        list.add("HanMeimei");

        //调用Collections中的方法，将list变为只读的
        List<String> newList = Collections.unmodifiableList(list);

        //newList.add("Tim");//可以编译，但是不能执行，否则报异常

        //遍历：jdk 8
        newList.forEach(System.out::println);
    }



    //jdk 8 以及之前：创建一个只读特点的集合
    @Test
    public void test2(){
        //只读List:
        List<Integer> list = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
        //list.add(4); //运行将报错，因为是只读的，不可变


        //只读Set:
        Set<Integer> set = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 2, 3)));
        //set.add(4);  //运行将报错，因为是只读的，不可变
        //set.forEach(System.out::println);


        //只读Map:
        Map<Object, Object> map = Collections.unmodifiableMap(new HashMap<>() {
            {
                put("Tom", 78);
                put("Jerry", 88);
                put("Tim", 68);
            }
        });
        //map.put("Tyo",90); //运行将报错，因为是只读的，不可变
        map.forEach((k,v) -> System.out.println(k + ":" + v));
    }




    //jdk 9 中：创建一个只读特点的集合
    @Test
    public void test3(){
        //只读List:
        List<Integer> list = List.of(1, 2, 3);
        //list.add(4); //运行将报错，因为是只读的
        list.forEach(System.out::println);


        //Set:
        Set<Integer> set = Set.of(2, 3, 4);
        //set.add(6); //运行将报错，因为是只读的

        //Map:
        //创建只读集合的方式一：
        Map<String, Integer> map = Map.of("Tom", 23, "Jerry", 22, "Lilei", 12, "HanMeimei", 18);
        //map.put("Tim",33);

        //创建只读集合的方式二：
        Map<String, Integer> map1 = Map.ofEntries(Map.entry("Tom", 23), Map.entry("Jerry", 21));
        //map1.put("Tim",33);

        System.out.println(map1);
    }
}
