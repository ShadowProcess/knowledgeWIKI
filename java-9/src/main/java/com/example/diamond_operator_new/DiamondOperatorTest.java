//package com.example.diamond_operator_new;
//
//import org.junit.Test;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 钻石操作符 <>
// */
//public class DiamondOperatorTest {
//
//    @Test
//    public void testDiamondOperator(){
//        diamondOperator();
//    }
//
//    public void diamondOperator(){
//        //Set<String> set = new HashSet<>(){};//JDK1.8编译不通过
//
//        Set<String> set = new HashSet<>(){};//JDK1.9编译通过; 因为有类型推断功能
//        set.add("MM");
//        set.add("JJ");
//        set.add("GG");
//        set.add("DD");
//
//        for(String s : set){
//            System.out.println(s);
//        }
//
//    }
//}
