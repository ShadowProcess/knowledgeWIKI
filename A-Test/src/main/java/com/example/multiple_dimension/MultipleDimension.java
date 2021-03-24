package com.example.multiple_dimension;

//Java为什么不允许多继承?

/**
 * 如果子类继承了 两个父类，两个父类中有相同的方法或者成员，在子类继承后，会造成歧义
 */
public class MultipleDimension extends AA {
    public static void main(String[] args) {
        new MultipleDimension().s();
    }
}



//接口可以多继承  如果
class MultipleInterface implements QQ,WW  {

    //QQ和WW中 都有default修饰的ss方法，这里会重写，所以不必担心有歧义
//    @Override
//    public void ss() {
//        System.out.println(QQ.s);
//        System.out.println(WW.s);
//    }
}

