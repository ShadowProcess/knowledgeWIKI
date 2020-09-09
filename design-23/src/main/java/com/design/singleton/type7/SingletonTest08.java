package com.design.singleton.type7;

public class SingletonTest08 {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance1 = Singleton.INSTANCE;
        System.out.println(instance == instance1);
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
        instance.sayOk();
    }
}


//使用枚举，可以实现单例
enum Singleton {
    INSTANCE;
    public void sayOk(){
        System.out.println("ok");
    }
}
