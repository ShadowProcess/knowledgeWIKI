package com.design.principle.interface_segregation.improve;

public class Segregation1 {
    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B());//A类通过接口依赖B类
        a.depend2(new B());
        a.depend3(new B());

        C c = new C();
        c.depend1(new D()); //C类通过接口去依赖（使用）D类
        c.depend4(new D());
        c.depend5(new D());
    }
}


//接口1
interface Interface1{
    void ope1();
}

//接口2
interface Interface2{
    void ope2();
    void ope3();
}

//接口3
interface Interface3{
    void ope4();
    void ope5();
}

class B implements Interface1,Interface2{
    @Override
    public void ope1() {
        System.out.println("1");
    }
    @Override
    public void ope2() {
        System.out.println("2");
    }
    @Override
    public void ope3() {
        System.out.println("3");
    }
}

class D implements Interface1,Interface3{
    @Override
    public void ope1() {
        System.out.println("1");
    }
    @Override
    public void ope4() {
        System.out.println("4");
    }
    @Override
    public void ope5() {
        System.out.println("5");
    }
}

class A { //A类通过接口Interface1,Interface2 依赖（使用）B类，但是只会用到1,2,3方法
    public void depend1(Interface1 i){
        i.ope1();
    }
    public void depend2(Interface2 i){
        i.ope2();
    }
    public void depend3(Interface2 i){
        i.ope3();
    }
}

class C { //C类通过接口Interface1,Interface3 依赖（使用）D类，但是只会用到1,4,5方法
    public void depend1(Interface1 i){
        i.ope1();
    }
    public void depend4(Interface3 i){
        i.ope4();
    }
    public void depend5(Interface3 i){
        i.ope5();
    }
}
