package com.design.singleton.type3;

public class SingletonTest03 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println(instance == instance1);
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}

//懒汉式-线程不安全

class Singleton{
    private static Singleton instance;

    private Singleton(){}

    //提供一个静态的公有方法，当使用该方法时，才去创建instance
    public static Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
