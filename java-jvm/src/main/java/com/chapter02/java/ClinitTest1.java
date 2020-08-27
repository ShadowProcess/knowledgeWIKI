package com.chapter02.java;


public class ClinitTest1 {
    static class Father{
        public static int A = 1;
        static{
            A = 2;
        }
    }

    static class Son extends Father{
        public static int B = A;
    }

    public static void main(String[] args) {
        //先加载Father类，其次加载Son类。
        System.out.println(Son.B);//2
    }
}
