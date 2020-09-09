package com.design.principle.liskov.improve;

public class Liskov {
    public static void main(String[] args) {
        A a = new A();
        System.out.println("11-3=" + a.fun1(11, 3));
        System.out.println("1-8=" + a.fun1(1, 8));

        System.out.println("---------");
        B b = new B();
        //因为B类不在继承A类，因此调用者，不会再认为func1是求减法
        System.out.println("11-3=" + b.fun1(11, 3)); //这里本意是求11+3
        System.out.println("1-8=" + b.fun1(1, 8));   //1+8
        System.out.println("11+3+9=" + b.fun2(11, 3));

        //使用组合仍然可以使用到A类的相关方法
        System.out.println("11-3="+b.fun3(11,3)); //这里本意是11-3
    }
}


//创建一个更加基础的基类
class Base{
    //把更加基础的方法和成员写到Base类
}

//A类
class A extends Base{
    //返回两个数的差
    public int fun1(int num1, int num2) {
        return num1 - num2;
    }
}

//B类继承A
//增加了一个新功能；完成两个数相加，然后和9求和
class B extends Base {
    //如果B需要使用A类的方法，使用组合的关系
    private A a = new A();

    //这里重写了A类的方法，可能是无意识的
    public int fun1(int num1, int num2) {
        return num1 + num2;
    }

    public int fun2(int a, int b) {
        return fun1(a, b) + 9;
    }

    //我们仍然想使用A的方法
    public int fun3(int a,int b){
        return this.a.fun1(a,b);
    }
}

