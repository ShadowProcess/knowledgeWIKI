package com.example.objects;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
import org.junit.Test;

//import java.util.Objects;

public class ObjectsDemo {

    //jdk原生
//    @Test
//    public void _0() {
//        Objects.equals("a", "a"); //true
//        Objects.equals(null, null); //true
//        System.out.println(Objects.hashCode("a"));
//
//        if (null == 1) {
//          语法错误
//        }
//    }

    //guava
    @Test
    public void _1() {
        int i = Objects.hashCode("a", "b", "c");
        System.out.println(i);
    }

    @Test
    public void _2(){
        int integer = Ints.tryParse("010");
        System.out.println(integer);
    }

    @Test
    public void _3(){
        System.out.println(Strings.isNullOrEmpty(null));
    }
}
