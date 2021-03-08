package com.example.simulatedeadlock;

public class Test {

    public static void main(String[] args) {
        int hour = 24;
        long mi = hour * 60 * 60 * 1000;
        long mic = hour * 60 * 60 * 1000 * 1000;
        System.out.println("mic:" + mic);
        System.out.println("mi:" + mi);
        System.out.println(mic / mi);
        /**
         * mic:500654080
         * mi:86400000
         * 5
         */
    }
}
