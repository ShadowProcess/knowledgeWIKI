package com.example.string;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class String1_8 {

    public static void main(String[] args) {

        String join = String.join(",", "1", "2", "3");
        System.out.println(join);

        List<String> strings = Arrays.asList("1", "3", "4", "56", "99");
        String join1 = String.join("-", strings);
        System.out.println(join1);

        StringJoiner sj = new StringJoiner("s", "pre", "suf");
        StringJoiner add = sj.add("1").add("2").add("3");
        System.out.println(add);


        String collect = Arrays.asList("2", "3", "4", "7", "6")
                .stream()
                .collect(Collectors.joining(","));
        System.out.println(collect);

        String collect1 = Arrays.asList("2", "3", "4", "7", "6")
                .stream()
                .collect(Collectors.joining(",","pre","suf"));
        System.out.println(collect1);
    }
}
