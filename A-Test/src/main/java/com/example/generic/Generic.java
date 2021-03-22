package com.example.generic;

import java.util.List;


/**
 * ? extends A  只要是A的子类都可以
 * ? super A    只要是A的父类就可以
 */
public class Generic {

    public static void main(String[] args) {
    }


    public class Food {
    }

    public class Fruit extends Food {
    }

    public class Apple extends Fruit {
    }

    public class Banana extends Fruit {
    }

    public class GenericTest {

        public void testExtends(List<? extends Fruit> list) {

            //报错,extends为上界通配符,只能取值,不能放.
            //因为Fruit的子类不只有Apple还有Banana,这里不能确定具体的泛型到底是Apple还是Banana，所以放入任何一种类型都会报错
            //list.add(new Apple());

            //可以正常获取
            Fruit fruit = list.get(1);
        }

        public void testSuper(List<? super Fruit> list) {

            //super为下界通配符，可以存放元素，但是也只能存放当前类或者子类的实例，以当前的例子来讲，
            //无法确定Fruit的父类是否只有Food一个(Object是超级父类)
            //因此放入Food的实例编译不通过
            list.add(new Apple());
//        list.add(new Food());

            Object object = list.get(1);
        }
    }
}
