jstat命令是使用频率比较高的命令，主要用来查看JVM运行时的状态信息，包括内存状态、垃圾回收等。

命令格式：
jstat [option] LVMID [interval] [count]

其中LVMID是进程id，interval是打印间隔时间（毫秒），count是打印次数（默认一直打印）

option参数解释：
-class class loader的行为统计
-compiler HotSpt JIT编译器行为统计
-gc 垃圾回收堆的行为统计
-gccapacity 各个垃圾回收代容量(young,old,perm)和他们相应的空间统计
-gcutil 垃圾回收统计概述
-gccause 垃圾收集统计概述（同-gcutil），附加最近两次垃圾回收事件的原因
-gcnew 新生代行为统计
-gcnewcapacity 新生代与其相应的内存空间的统计
-gcold 年老代和永生代行为统计
-gcoldcapacity 年老代行为统计
-gcpermcapacity 永生代行为统计
-printcompilation HotSpot编译方法统计

常用示例及打印字段解释：
jstat -gcutil 11666 1000 3
11666为pid，每隔1000毫秒打印一次，打印3次



# jstat -gcutil 7680

    示例输出：

    S0       S1      E       O        P        YGC    YGCT    FGC     FGCT     GCT

    75.02    0.00    3.34    41.42    99.92    388    15.379   20     0.000    15.379

    其中：

    S0 S1：Survivor0，Survivor1空间占百分比。  

    E：Eden空间使用所占百分比。

    O：老年代空间使用所占百分比。

    P：永久代空间使用所占百分比。

    YGC：自从进程启动以来发生的Minor GC次数（YGC表示Young GC)。

    YGCT：自从进程启动以来发生的Minor GC总耗时（秒）。

    FGC：自从进程启动以来发生的Full GC次数。

    FGCT：自从进程启动以来发生的Full GC总耗时（秒）。

    GCT：自从进程启动以来所有GC总耗时（秒）。
    
    
# jstat -gccause 4882

    示例输出：

    S0       S1     E        O         P        YGC    YGCT     FGC     FGCT     GCT       LGGC                     GCC

    75.02  0.00  3.34   41.42   99.92  388     15.379     0     0.000      15.379    Allocation Failure   No GC

    其中：

    LGGC:最近一次GC的原因。

    其他同上。    