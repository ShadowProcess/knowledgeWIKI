package com.example.flatmap;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExploreDemo {


    @Test
    public void flatDemo1() {
        //int[][] arr = new int[][]{{1, 2, 3}, {4, 5}, {6, 7, 8}};

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);

        List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

        List<Integer> list = listOfLists.stream()
                .flatMap(x -> x.stream())   ////首先把上游发来的元素转化成流，然后把流中元素遍历给下游
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void flatDemo2() {
        String[][] a = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};

        List<String> collect = Arrays.stream(a)
                .flatMap(it -> Arrays.stream(it)) //首先把上游发来的元素转化成流，然后把流中元素遍历给下游
                .collect(Collectors.toList());

        System.out.println(collect);
    }

}
