package com.example.multithread.threadlocal_study;

/**
 * 首先，是否使用private修饰与ThreadLocal本身无关，也就是说，是否使用private修饰是一个普遍的问题，而不是与ThreadLocal有关的一个具体问题
 * 其次，ThreadLocal一般会采用static修饰，这样做既有好处，也有坏处，
 * 好处是它一定程度上可以避免错误，至少它可以避免重复创建TSO(Thread Specific Object,即ThreadLocal所关联的对象)所导致的浪费，
 * 坏处是这样做可能正好形成内存泄漏所需要的条件。
 *
 * 这个结论分析如下：
 * 我们知道，一个ThreadLocal实例对应当前线程的一个TSO实例，因此，如果把ThreadLocal声明为某个类的实例变量(而不是静态变量)，
 * 那么每创建一个该类的实例就会导致一个新的TSO实例被创建。显然，这些被创建的TSO实例是同一个类的实例。
 * 于是，同一个线程可能会访问到同一个TSO(指类)的不同实例，这样便会导致错误，也会导致浪费(重复创建等同的对象)! 因此，
 * 一般我们将ThreadLocal使用static修饰即可。
 *
 */

public class ThreadLocalTest {

    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>() {
        /**
         * ThreadLocal没有被当前线程赋值时,或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected Object initialValue() {
            System.out.println("调用get方法时，如果当前线程共享变量没有设置值，调用initialValue获取默认值！");
            return null;
        }
    };


    public static void main(String[] args) {
        new Thread(new MyIntegerTask("IntegerTask1")).start();
        new Thread(new MyStringTask("StringTask1")).start();
        new Thread(new MyIntegerTask("IntegerTask2")).start();
        new Thread(new MyStringTask("StringTask2")).start();
    }

    public static class MyIntegerTask implements Runnable {
        private final String name;

        MyIntegerTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                // ThreadLocal.get方法获取线程变量
                if (null == ThreadLocalTest.threadLocal.get()) {
                    // ThreadLocal.et方法设置线程变量
                    ThreadLocalTest.threadLocal.set(0);
                    System.out.println("线程：" + name + "先将值初始为0");
                } else {
                    int num = (Integer) ThreadLocalTest.threadLocal.get();
                    ThreadLocalTest.threadLocal.set(num + 1);
                    System.out.println("线程：" + name + "将值+1");
                    System.out.println("线程：" + name + "，获取到的值为" + ThreadLocalTest.threadLocal.get());
                    if (i == 3) {
                        ThreadLocalTest.threadLocal.remove();
                        System.out.println("线程：" + name + "将值移除，调用remove方法");
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class MyStringTask implements Runnable {
        private final String name;

        MyStringTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                if (null == ThreadLocalTest.threadLocal.get()) {
                    ThreadLocalTest.threadLocal.set("a");
                    System.out.println("线程：" + name + "先将值初始为a");
                } else {
                    String str = (String) ThreadLocalTest.threadLocal.get();
                    ThreadLocalTest.threadLocal.set(str + "a");
                    System.out.println("线程：" + name + "将值+a");
                    System.out.println("线程：" + name + "，获取到的值为" + ThreadLocalTest.threadLocal.get());
                }
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
