package com.design.principle.liskov;

public class Liskov {
    public static void main(String[] args) {
        A a = new A();
        System.out.println("11-3=" + a.fun1(11, 3));
        System.out.println("1-8=" + a.fun1(1, 8));

        System.out.println("---------");
        B b = new B();
        System.out.println("11-3=" + b.fun1(11, 3)); //这里本意是求11-3
        System.out.println("1-8=" + b.fun1(1, 8));   //1-8
        System.out.println("11+3+9=" + b.fun2(11, 3));
    }
}


//A类
class A {
    //返回两个数的差
    public int fun1(int num1, int num2) {
        return num1 - num2;
    }
}

//B类继承A
//增加了一个新功能；完成两个数相加，然后和9求和
class B extends A {
    //这里重写了A类的方法，可能是无意识的
    public int fun1(int num1, int num2) {
        return num1 + num2;
    }

    public int fun2(int a, int b) {
        return fun1(a, b) + 9;
    }
}

