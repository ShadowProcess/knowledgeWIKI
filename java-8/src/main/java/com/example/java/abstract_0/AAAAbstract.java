package com.example.java.abstract_0;

//
//多态的前提:
//        A:要有继承关系
//        B:要有方法重写
//        C:要有父类引用指向子类的对象。
//        父 f = new 子();
//
//多态中成员访问的特点分别是什么?
//        成员变量：
//        编译看左边(父类)，运行看左边(父类)
//        成员方法：
//        编译看左边(父类)，运行看右边(子类)。
//        静态方法：
//        编译看左边(父类)，运行看左边(父类)。
//        (静态和类相关，算不上重写，所以，访问还是左边的)
//        只有非静态的成员方法,编译看左边,运行看右边


//抽象类特点
//
//        （1）抽象类不能实例化，即不能对其用new运算符；
//
//        （2） 类中如果有一个或多个abstract方法，则该类必须声明为abstract；
//
//        （3）抽象类中的方法不一定都是abstract方法，它还可以包含一个或者多个具体的方法；
//
//        （4）即使一个类中不含抽象方法，它也可以声明为抽象类；
//
//        （5）抽象类中的抽象方法要被使用，必须由子类复写起所有的抽象方法后，建立子类对象调用。
//
//        （6）如果子类只覆盖了部分抽象方法，那么该子类还是一个抽象类。


//抽象类关键字abstract不可以和哪些关键字共存?
//
//        1.private：
//        私有的方法子类是无法继承到的，也不存在覆盖，而abstract和private一起使用修饰方法，abstract既要子类去实现这个方法，而private修饰子类根本无法得到父类这个方法。互相矛盾。
//
//        2.final:
//        抽象类不能和final共存,因为抽象类自身无法创建对象,我们需要通过子类创建对象,一旦抽象类使用final关键字,那么抽象类就没有子类
//        抽象方法不能和final共存,因为抽象方法后期需要被子类重写,一旦加final无法重写
//
//        3.static:
//        抽象方法不能和static关键字共存,因为一旦加static我们就可以通过类名直接访问抽象方法,由于抽象方法没有方法体,没有任何意义,也不允许这样做
public abstract class AAAAbstract {

    public AAAAbstract() {
    }

    int p = 0;

    public int u = 0;

    public static int i = 0;

    public abstract void a();

    public abstract void f();

    public void b() {
        System.out.println("b");
    }

    public static void c() {
        System.out.println("c");
    }

    public static void main(String[] args) {
        /**
         * 抽象类如果想new，那么需要用匿名内部类的方式，并要实现抽象方法；
         */
//        AAAAbstract aaaAbstract = new AAAAbstract() {
//            @Override
//            public void a() {
//
//            }
//        };

        /**
         * 如果抽象类没有抽象方法，也不能直接new
         * 编译错误  'AAAAbstract' is abstract; cannot be instantiated
         */
        //AAAAbstract aaaAbstract1 = new AAAAbstract();

        c();
    }
}



//子类只实现了部分抽象方法，那么该子类还是抽象的
abstract class B extends AAAAbstract {
    @Override
    public void a() {

    }
}
