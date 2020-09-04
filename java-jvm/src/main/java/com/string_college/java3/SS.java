package com.string_college.java3;


/**
 *
 * 思考：
 * new String("a") + new String("b")呢？
 *  对象1：new StringBuilder()
 *  对象2： new String("a")
 *  对象3： 常量池中的"a"
 *  对象4： new String("b")
 *  对象5： 常量池中的"b"
 *  深入剖析： StringBuilder的toString():
 *      对象6 ：new String("ab")
 *       强调一下，toString()的调用，在字符串常量池中，没有生成"ab"   (toString方法调用，在字符串常量池中没有生成"ab",因为字节码指令没有ldc)
 */
public class SS {
    public static void main(String[] args) {
        String s = new String("a")+new String("b"); 
        String s2 = s.intern();
        System.out.println(s2 == "ab");
        System.out.println(s == "ab");
    }
}
