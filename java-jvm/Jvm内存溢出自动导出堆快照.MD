
#在jvm启动参数添加，程序在内存溢出时会将堆快照导出放置在D盘下
-Xmx10m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:/

```
-Xms                        是指程序启动时初始内存大小（此值可以设置成与-Xmx相同，以避免每次GC完成后 JVM 内存重新分配）。
-Xmx                        指程序运行时最大可用内存大小，程序运行中内存大于这个值会 OutOfMemory。
-Xmn                        年轻代大小（整个JVM内存大小 = 年轻代 + 年老代 + 永久代）。
-XX:NewRatio                年轻代与年老代的大小比例，-XX:NewRatio=4 设置为4，则年轻代与年老代所占比值为1：4。
-XX:SurvivorRatio           年轻代中Eden区与Survivor区的大小比值，-XX:SurvivorRatio=4，设置为4，则两个Survivor区与一个Eden区的比值为 2:4
-Xss                        设置每个线程的堆栈大小。
-XX:MaxPermSize             设置永久代大小。

-XX:MaxTenuringThreshold    设置垃圾最大年龄（最大值为15）。
如果设置为0的话，则年轻代对象不经过Survivor区，直接进入年老代。对于年老代比较多的应用，可以提高效率。
如果将此值设置为一个较大值，则年轻代对象会在Survivor区进行多次复制，这样可以增加对象再年轻代的存活时间，增加在年轻代即被回收的概论。

用法: -XX:MaxTenuringThreshold=15
该参数主要是控制新生代需要经历多少次GC晋升到老年代中的最大阈值。在JVM中用4个bit存储（放在对象头中），（1111）所以其最大值是15。
但并非意味着，对象必须要经历15次YGC才会晋升到老年代中。例如，当survivor区空间不够时，便会提前进入到老年代中，但这个次数一定不大于设置的最大阈值。

那么JVM到底是如何来计算S区对象晋升到Old区的呢？
首先介绍另一个重要的JVM参数：
-XX:TargetSurvivorRatio：一个计算期望s区存活大小(Desired survivor size)的参数。默认值为50，即50%。
当一个S区中所有的age对象的大小如果大于等于Desired survivor size，则重新计算threshold，以age和MaxTenuringThreshold两者的最小值为准。
```