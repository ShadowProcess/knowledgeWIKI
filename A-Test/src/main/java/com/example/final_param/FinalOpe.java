package com.example.final_param;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FinalOpe {

    /**
     * JAVA方法中的参数用final来修饰的效果
     * 很多人都说在JAVA中用final来修饰方法参数的原因是防止方法参数在调用时被篡改，其实也就是这个原因，
     * 但理解起来可能会有歧义，我们需要注意的是，在final修饰的方法参数中，如果修饰的是基本类型，
     * 那么在这个方法的内部，基本类型的值是不能够改变的，但是如果修饰的是引用类型的变量，
     * 那么就需要注意了，引用类型变量所指的引用是不能够改变的，但是引用类型变量的值是可以改变的。
     *
     *
     * <注意：引用类型指向不能改变，但是变量的值可以修改/>
     * public static void checkInt(final User user)
     *     {
     *         //user变量的引用是不能够改变的，否则的话，编译会报错
     *         //    user=new User();
     *         //user变量的值是能够修改的，但所指向的引用是不能够改变的
     *         user.setUserName("小王");
     *     }
     *
     */
//    @Test
//    public void _00(final String s){
//        System.out.println(s);
//    }

    //final修饰的int和integer比较
    @Test
    public void i(){
        final int i = 1;
        Integer s = 1;
        System.out.println(i == s); //true
        System.out.println(Objects.equals(i,s)); //true
    }
    //final修饰的integer比较 int
    @Test
    public void it(){
        final Integer i = 1;
        int s = 1;
        System.out.println(s == i); //true
        System.out.println(Objects.equals(s,i)); //true
    }


    @Test
    public void f(){
        final double a = Math.random();
        System.out.println(a);
    }


    @Test
    public void f1(){
        final int i = 1;
        Set<Integer> s = new HashSet<Integer>(){};
        s.add(1);
        s.add(2);
        System.out.println(s.contains(i));
    }

    @Test
    public void f2(){
        List list = null;
        for (Object o : list) {
            System.out.println(o);
        }
    }

}
