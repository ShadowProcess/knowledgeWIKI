package com.example.stream.binaryoperator;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 *
 * BinaryOperator 是BiFunction 的三参数特殊化形式,两个入参和返回结果都是类型T
 *
 */
public class BinaryOperatorTest {

    @Test
    public void test1() {
        BinaryOperator<Integer> add = (n1, n2) -> n1 + n2;
        //apply方法用于接收参数，并返回BinaryOperator中的Integer类型
        System.out.println(add.apply(3, 4));
    }

    @Test
    public void test3(){
        BinaryOperator<String> addStr = (n1, n2) -> n1 +"==="+ n2;
        //apply方法用于接收参数，并返回BinaryOperator中的String类型
        System.out.println(addStr.apply("3", "4"));
    }

    @Test
    public void test4(){
        //自然排序Comparator.naturalOrder()
        BinaryOperator<Integer> bi = BinaryOperator.minBy(Comparator.naturalOrder());
        System.out.println(bi.apply(2, 3));
        //返回2
    }

    @Test
    public void test5(){
        //自然排序Comparator.naturalOrder()
        BinaryOperator<Integer> bi = BinaryOperator.maxBy(Comparator.naturalOrder());
        System.out.println(bi.apply(2, 3));
        //返回3
    }


}
