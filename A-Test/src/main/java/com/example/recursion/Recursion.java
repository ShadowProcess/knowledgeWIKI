package com.example.recursion;

//斐波那契数列求解算法
public class Recursion {

    public static void main(String[] args) {
        System.out.println(recursion(20));
    }

    public static int recursion(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return recursion(n-1) + recursion(n-2);
    }
}
