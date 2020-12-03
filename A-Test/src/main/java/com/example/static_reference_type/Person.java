package com.example.static_reference_type;

public class Person {
    public static void main(String[] args) {
        MainEntry self = MainEntry.self();
        System.out.println("self:" + self); //self:null
        self.staticVoid();    //可以正常运行
        self.notStaticVoid(); //报错
    }
}


//TODO 原因：可以正常运行
// 原因：从class文件可以看出，在编译的时候JVM发现调用静态方法的对象为null值后，
// 自动将null值的对象替换为了类名调用。这就是控制台之所以能打印出hello的原因 。
// 以下是编译过后的Class文件
//package com.example.static_reference_type;
//
//public class Person {
//    public Person() {
//    }
//
//    public static void main(String[] args) {
//        MainEntry self = MainEntry.self();
//        System.out.println("self:" + self);
//        MainEntry.staticVoid();
//    }
//}