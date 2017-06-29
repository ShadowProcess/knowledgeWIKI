package com.example.stream.predicate;

import java.util.function.Predicate;

/**
 * Predicate接口在stream里面用的比较多, 感兴趣的可以去看看stream, java 8 里另一个新的东西,很好玩.
 * */

/**
 * Predicate 有参数,返回值为boolean类型  true|false
 */
public class PredicateDemo {
    public static void main(String[] args) {

        Predicate<String> predicate = (x) -> x.length() > 0;
        System.out.println(predicate.test("helloPredicate"));

    }
}
