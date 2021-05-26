jinfo是用来查看JVM参数和动态修改部分JVM参数的命令

命令格式：
jinfo [option] <pid>

options参数解释：
- flag <name> 打印指定名称的参数
- flag [+|-]<name> 打开或关闭参数
- flag <name>=<value> 设置参数
- flags 打印所有参数
- sysprops 打印系统配置
<no option> 打印上面两个选项

最常用示例：
其中11666为pid

查看JVM参数和系统配置
jinfo 11666
jinfo -flags 11666
jinfo -sysprops 11666

查看打印GC日志参数
jinfo -flag PrintGC 11666
jinfo -flag PrintGCDetails 11666

打开GC日志参数
jinfo -flag +PrintGC 11666
jinfo -flag +PrintGCDetails 11666

关闭GC日志参数
jinfo -flag -PrintGC 11666
jinfo -flag -PrintGCDetails 11666

还可以使用下面的命令查看那些参数可以使用jinfo命令来管理：
java -XX:+PrintFlagsFinal -version | grep manageable

-Xms：初始堆大小，默认为物理内存的1/64(<1GB)；默认(MinHeapFreeRatio参数可以调整)空余堆内存小于40%时，JVM就会增大堆直到-Xmx的最大限制
-Xmx：最大堆大小，默认(MaxHeapFreeRatio参数可以调整)空余堆内存大于70%时，JVM会减少堆直到 -Xms的最小限制
-Xmn：新生代的内存空间大小，注意：此处的大小是（eden+ 2 survivor space)。与jmap -heap中显示的New gen是不同的。整个堆大小=新生代大小 + 老生代大小 + 永久代大小。
      在保证堆大小不变的情况下，增大新生代后,将会减小老生代大小。此值对系统性能影响较大,Sun官方推荐配置为整个堆的3/8。
-XX:SurvivorRatio：新生代中Eden区域与Survivor区域的容量比值，默认值为8。两个Survivor区与一个Eden区的比值为2:8,一个Survivor区占整个年轻代的1/10。
-Xss：每个线程的堆栈大小。JDK5.0以后每个线程堆栈大小为1M,以前每个线程堆栈大小为256K。应根据应用的线程所需内存大小进行适当调整。在相同物理内存下,
      减小这个值能生成更多的线程。但是操作系统对一个进程内的线程数还是有限制的，不能无限生成，经验值在3000~5000左右。一般小的应用， 如果栈不是很深， 应该是128k够用的，
      大的应用建议使用256k。这个选项对性能影响比较大，需要严格的测试。和threadstacksize选项解释很类似,官方文档似乎没有解释,
      在论坛中有这样一句话:"-Xss is translated in a VM flag named ThreadStackSize”一般设置这个值就可以了。
-XX:PermSize：设置永久代(perm gen)初始值。默认值为物理内存的1/64。