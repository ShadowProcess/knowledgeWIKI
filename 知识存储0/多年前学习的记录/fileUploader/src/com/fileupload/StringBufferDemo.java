package com.fileupload;


import org.junit.Test;

/**
 * java参数传递  处理8种基本数据类型,是值传递,其它都是引用传递
 *
 */

public class StringBufferDemo {

    public static void main(String[] args) {
        System.out.println("1.2.3".replaceAll(".","-"));

        StringBuffer sb1 = new StringBuffer("hello");
        StringBuffer sb2 = new StringBuffer("world");
        System.out.println(sb1 + "--after--" + sb2);//hello----world
        change(sb1, sb2);
        System.out.println(sb1 + "--before--" + sb2);//hello----worldworld

    }


    public static void change(StringBuffer sb1, StringBuffer sb2) {
        // TODO Auto-generated method stub
        sb1 = sb2;  // 并不能修改sb1的值
        sb2.append(sb1);
    }



    @Test
    public void testTwo(){

    }



    @Test
    void testOne(){
        String s1 = "hello";  // 等同于 String s1 = new String("hello");
        String s2 = "world";
        System.out.println(s1 + "----" + s2);//hello----world

        change(s1, s2); // 不能改变s1 和 s2的值，虽然String也是传递的地址，但由于String不可变的特性
        System.out.println(s1 + "----" + s2);//hello----world
    }
    /**
     *
     * String字符串在内存不可变特性
     * @param s1
     * @param s2
     */
    public static void change(String s1, String s2) {
        // TODO Auto-generated method stub
        s1 = s2;
        s2 = s1 + s2;
    }





}



