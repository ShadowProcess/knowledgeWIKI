# ThreadLocal 和 InheritableThreadLocal
父子线程共享数据可以通过使用 InheritableThreadLocal 来达成目的，InheritableThreadLocal 类继承于 ThreadLocal
```
InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
```
用法和ThreadLocal一样


#原理
Thread源码中有写，当创建子线程时，如果父线程有inheritThreadLocals，那么复制一份到子线程
```
/**
 * 初始化一个线程.
 * 此函数有两处调用，
 * 1、上面的 init()，不传AccessControlContext，inheritThreadLocals=true
 * 2、传递AccessControlContext，inheritThreadLocals=false
 */
private void init(ThreadGroup g, Runnable target, String name,
                  long stackSize, AccessControlContext acc,
                  boolean inheritThreadLocals) {
    ......（其他代码）

    if (inheritThreadLocals && parent.inheritableThreadLocals != null)
        this.inheritableThreadLocals =
            ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);

    ......（其他代码）
}
```
