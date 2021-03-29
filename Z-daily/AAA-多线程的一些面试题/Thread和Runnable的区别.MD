1、Thread实现了Runnable接口

2、Runnable更像是创建线程的一种实现，具体执行还是要用Thread

3、Thread的start方法才是真正的创建线程，start方法调用start0()方法，start0()是一个native方法，才是真正创建线程

4、实现Runnable接口的同时，还可以继承其他类，避免Java的单继承性带来局限性
