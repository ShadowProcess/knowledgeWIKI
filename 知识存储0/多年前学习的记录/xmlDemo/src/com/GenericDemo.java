package com;

public class GenericDemo {

    public static void main(String[] args) {
        run(12.3);
    }

    public static <T> void run(T t) {
        System.out.println(t.toString());
    }
}
