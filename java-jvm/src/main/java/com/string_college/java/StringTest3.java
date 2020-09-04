package com.string_college.java;

import java.util.HashSet;
import java.util.Set;

/**
 * jdk6中：
 * 设置永久代大小
 * -XX:PermSize=6m -XX:MaxPermSize=6m
 * 设置堆空间大小
 * -Xms6m -Xmx6m
 *
 * jdk8中：
 * 设置元空间大小
 * -XX:MetaspaceSize=6m -XX:MaxMetaspaceSize=6m
 * 设置堆空间大小
 * -Xms6m -Xmx6m
 */
public class StringTest3 {
    public static void main(String[] args) {
        //使用Set保持着常量池引用，避免full gc回收常量池行为
        Set<String> set = new HashSet<String>();
        //在short可以取值的范围内足以让6MB的PermSize或heap产生OOM了。
        short i = 0;
        while(true){
            set.add(String.valueOf(i++).intern());
        }
    }
}
