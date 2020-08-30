package com.stack_structure_college.java1;


//动态链接（或指向运行时常量池的方法引用）

public class DynamicLinkingTest {

    int num = 10;

    public void methodA(){
        System.out.println("methodA()....");
    }

    public void methodB(){
        System.out.println("methodB()....");

        methodA();

        num++;
    }

}
