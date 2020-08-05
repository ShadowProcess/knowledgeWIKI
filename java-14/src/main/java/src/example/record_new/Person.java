package src.example.record_new;


public record Person(String name,Person partner) {

    //还可以声明静态的属性、静态的方法、构造器、实例方法

    public static String nation;

    public static String showNation(){
        return nation;
    }

    public Person(String name){
        this(name,null);
    }

    public String getNameInUpperCase(){
        return name.toUpperCase();
    }
    //不可以声明非静态的属性
//    private int id;//报错
}


//不可以将record定义的类声明为abstract的
//abstract record Order(){
//
//}


//不可以给record定义的类声明显式的父类（非Record类）
//record Order() extends Thread{
//
//}


// 上述代码编译后的字节码源码
//public final class Person extends java.lang.Record {
//    private final java.lang.String name;
//    private final src.example.record_new.Person partner;
//    public static java.lang.String nation;
//
//    public Person(java.lang.String name) { /* compiled code */ }
//
//    public Person(java.lang.String name, src.example.record_new.Person partner) { /* compiled code */ }
//
//    public static java.lang.String showNation() { /* compiled code */ }
//
//    public java.lang.String getNameInUpperCase() { /* compiled code */ }
//
//    public java.lang.String toString() { /* compiled code */ }
//
//    public final int hashCode() { /* compiled code */ }
//
//    public final boolean equals(java.lang.Object o) { /* compiled code */ }
//
//    public java.lang.String name() { /* compiled code */ }
//
//    public src.example.record_new.Person partner() { /* compiled code */ }
//}