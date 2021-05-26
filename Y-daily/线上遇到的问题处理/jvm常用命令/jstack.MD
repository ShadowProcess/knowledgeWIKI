jstack是用来查看JVM线程快照的命令，线程快照是当前JVM线程正在执行的方法堆栈集合。使用jstack命令可以定位线程出现长时间卡顿的原因，例如死锁，死循环等。jstack还可以查看程序崩溃时生成的core文件中的stack信息。

命令格式：
jstack [-l] <pid> (连接运行中的进程)
jstack -F [-m] [-l] <pid> (连接挂起的进程)
jstack [-m] [-l] <executable> <core> (连接core文件)
jstack [-m] [-l] [server_id@]<remote server IP or hostname> (连接远程debug服务器)

option参数解释：
-F 当使用jstack <pid>无响应时，强制输出线程堆栈。
-m 同时输出java和本地堆栈(混合模式)
-l 额外显示锁信息

常用示例：
jstack -l 11666 | more

输出信息：
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.25-b02 mixed mode):

"Attach Listener" #25525 daemon prio=9 os_prio=0 tid=0x00007fd374002000 nid=0x70e8 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

   Locked ownable synchronizers:
    - None