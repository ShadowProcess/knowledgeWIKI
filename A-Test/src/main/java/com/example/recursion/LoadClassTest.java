package com.example.recursion;

//观察类的加载顺序

/**
 * 主类静态代码块
 * 内部类静态代码块
 * 内部类普通代码块
 * 内部类构造
 * 内部类方法
 */
public class LoadClassTest {
    static {
        System.out.println("主类静态代码块");
    }
    {
        System.out.println("主类普通代码块");
    }

    public static void main(String[] args) {
        new Outer().SS();
    }


    static class Outer{
        static {
            System.out.println("内部类静态代码块");
        }
        {
            System.out.println("内部类普通代码块");
        }

        public Outer(){
            System.out.println("内部类构造");
        }

        public void SS(){
            System.out.println("内部类方法");
        }

    }
}
