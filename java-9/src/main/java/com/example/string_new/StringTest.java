package com.example.string_new;

/**
 * char  两个字节    16bit
 * byte  一个字节    8bit
 * (通过java研究发现，大部分字符都是拉丁字符，而拉丁字符只需要一个字节就可以存储，使用char就浪费一半的内存)
 * (那么中文怎么存储呢，因为中文一个字节存储不下，那么需要配合encoding flag使用)
 *
 * UTF-16 哪怕英文也是一个字节，也用两个字节存储，固定使用两个字节存储
 * UTF-8  它是动态的，如果英文，那么用一个字节存储，中文就用两个字节
 *
 * String：
 * jdk 8 及之前：底层使用char[]存储；
 * jdk 9 : 底层使用byte[] (并且指定encoding flag)
 *
 * StringBuffer:
 * jdk 8 及之前：底层使用char[]存储；
 * jdk 9 : 底层使用byte[]
 *
 * StringBuilder:
 * jdk 8 及之前：底层使用char[]存储；
 * jdk 9 : 底层使用byte[]
 *
 * String：不可变的字符序列；
 * StringBuffer:可变的字符序列；线程安全的，效率低；
 * StringBuilder:可变的字符序列；线程不安全的，效率高（jdk 5.0)
 */
public class StringTest {
    public void _0(){
        String s = "s";
    }
}
