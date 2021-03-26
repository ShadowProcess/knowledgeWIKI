package com.example.abstract_test;

public abstract class ARP {

    //抽象类可以有构造方法
    public ARP() {
    }

    //抽象类的普通成员
    public String ss = "ss";

    //抽象类的静态成员
    public static final String s = "s";

    //抽象类中的抽象方法没有实现；
    abstract void bb();

    //抽象类中的普通方法,有具体实现
    public void aa() {
        System.out.println("抽象类中的普通方法必须要有实现");
    }

    public static void main(String[] args) {
        ARP arp = new ARP() {
            @Override
            void bb() {
                System.out.println("");
            }
        };
        System.out.println(ARP.s);
    }
}
