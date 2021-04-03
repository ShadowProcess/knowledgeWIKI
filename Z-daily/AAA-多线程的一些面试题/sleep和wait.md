sleep和wait的区别在于这两个方法来自不同的类分别是Thread和Object，
sleep方法没有释放锁，而wait方法释放了锁，
使得其他线程可以使用同步控制块或者方法。
wait只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)。


sleep的作用

sleep的作用是让线程休眠制定的时间，在时间到达时恢复，也就是说sleep将在接到时间到达事件事恢复线程执行。

wait的作用

调用wait方法将会将调用者的线程挂起，直到其他线程调用同一个对象的notify方法才会重新激活调用者。

sleep与wait差异总结

1、来自不同的类：sleep是Thread的静态类方法，谁调用的谁去睡觉，即使在a线程里调用了b的sleep方法，实际上还是a去睡觉，要让b线程睡觉要在b的代码中调用sleep。

2、有没有释放锁(释放资源)：sleep不出让系统资源;wait是进入线程等待池等待，出让系统资源，其他线程可以占用CPU。

3、一般wait不会加时间限制，因为如果wait线程的运行资源不够，再出来也没用，要等待其他线程调用notify/notifyAll唤醒等待池中的所有线程，才会进入就绪队列等待OS分配系统资源。sleep(milliseconds)可以用时间指定使它自动唤醒过来，如果时间不到只能调用interrupt()强行打断。

4、sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常。