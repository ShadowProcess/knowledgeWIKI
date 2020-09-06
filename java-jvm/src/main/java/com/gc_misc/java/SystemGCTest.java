package com.gc_misc.java;

/**

  调用System.gc()，不能确定是否马上执行gc
  但是加上System.runFinalization()的调用；表示强制调用使用引用的对象的finalize()方法

  System.gc();下面调用的是Runtime.getRuntime().gc()
 */
public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();
        System.gc();//提醒jvm的垃圾回收器执行gc,但是不确定是否马上执行gc
        //与Runtime.getRuntime().gc();的作用一样。

//        System.runFinalization();//强制调用使用引用的对象的finalize()方法
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("SystemGCTest 重写了finalize()");
    }
}
