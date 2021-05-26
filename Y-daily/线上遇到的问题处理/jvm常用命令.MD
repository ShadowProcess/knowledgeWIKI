################################################################################################
~~~
jstat	JVM Statistics Monitoring Tool,jstat是用于监视虚拟各种运行状态信息的命令行工具，它可以显示本地或者远程虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据。
jinfo	Configuration info for java，命令的作用是实时的查看和调整虚拟机的参数。
jmap	Memory Map for java，生成虚拟机的内存转储快照(heapdump)
jhat	JVM Heap Dump Browser，用于分析heapdump文件，它会建立一个Http/HTML服务器，让用户可以在浏览器上查看分析结果
jstack	Stack Trace for java，显示虚拟机的线程快照。

使用--help，查看命令具体使用

常用：
jps -v
jstat -gc 118694 500 5 
jmap -dump:live,format=b,file=dump.hprof 29170
jmap -heap 29170
jmap -histo:live 29170 | more
jmap -permstat 29170
jstack -l 29170 |more



####################### 线程block(阻塞)、线程数暴涨
jstack -l pid |wc -l
jstack -l pid |grep "BLOCKED"|wc -l
jstack -l pid |grep "Waiting on condition"|wc -l

线程block问题通常是等待io、等待网络、等待监视器锁等造成，可能会导致请求超时、造成造成线程数暴涨导致系统502等。
假设出现这样的问题，主要是关注jstack 出来的BLOCKED、Waiting on condition、Waiting on monitor entry等状态信息。
假设大量线程在“waiting for monitor entry”：可能是一个全局锁堵塞住了大量线程。
假设短时间内打印的 thread dump 文件反映。随着时间流逝。waiting for monitor entry 的线程越来越多，没有降低的趋势，可能意味着某些线程在临界区里呆的时间太长了，以至于越来越多新线程迟迟无法进入临界区。
假设大量线程在“waiting on condition”：可能是它们又跑去获取第三方资源，迟迟获取不到Response，导致大量线程进入等待状态。
假设发现有大量的线程都处在 Wait on condition，从线程堆栈看，正等待网络读写，这可能是一个网络瓶颈的征兆，由于网络堵塞导致线程无法运行。




###线程状态分析
值得关注的线程状态有：

① 死锁：Deadlock（重点关注）
② 执行中：Runnable
③ 等待资源：Waiting on condition（重点关注）
④ 等待获取监视器：Waiting on monitor entry（重点关注）
⑤ 暂停：Suspended
⑥ 对象等待中：Object.wait() 或 TIMED_WAITING
⑦ 阻塞：Blocked（重点关注）
⑧ 停止：Parked