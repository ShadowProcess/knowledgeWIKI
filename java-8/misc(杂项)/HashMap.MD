###HashMap变化
jdk1.7 HashMap[数组+链表]

jdk1.8 HashMap[数组+链表+红黑树]
- 当链表超过8时会进化为红黑树
- 当链表小于6时,会从红黑树退化为链表

-----------------------------(为什么到8进化为红黑树，为6时退化为链表)--------------------------------------------------------------------
HashMap源码：
[因此在链表节点数量大于8时，会将链表转化为红黑树进行存储。这样一来，查询的时间复杂度就会由原先的O(n)变为O(log2(n))]
static final int TREEIFY_THRESHOLD = 8; 
[在哈希表扩容时，如果发现链表长度 <= 6，则会由树重新退化为链表。设置为6猜测是因为时间和空间的权衡
当链表长度为6时：查询的平均长度为：n/2=3，红黑树：log2(6) = 2.6 
当链表长度为8时：
链表：8/2=4， 红黑树：log2(8) = 3]
static final int UNTREEIFY_THRESHOLD = 6;
--------------------------------------------------------------------------------------------------------------------------------

###ConcurrentHashMap变化
jdk1.7 ConcurrentHashMap
- [并发级别concurrentLevel=16（锁）,数组分段的隔离锁，数据结构采用：数组+链表]
jdk1.8 ConcurrentHashMap
- [JDK1.8的实现已经抛弃了分段锁机制，利用Node+CAS+Synchronized来保证并发更新的安全。数据结构采用：数组+链表+红黑树。]

###具体原理：
ConcurrentHashMap初始化时，计算出Segment数组的大小size和每个Segment中HashEntry数组的大小cap，
并初始化Segment数组的第一个元素；其中size大小为2的幂次方，默认为16，cap大小也是2的幂次方，最小值为2，
最终结果根据根据初始化容量initialCapacity进行计算，其中(Segment在实现上继承了ReentrantLock)，这样就自带了锁的功能。

>然而在jdk1.8中的实现已经抛弃了Segment分段锁机制，利用CAS+Synchronized来保证并发更新的安全，底层依然采用数组+链表+红黑树的存储结构。
>当执行put方法插入数据时，根据key的hash值，在Segment数组中找到相应的位置，如果相应位置的Segment还未初始化，则通过CAS进行赋值，
接着执行Segment对象的put方法通过加锁机制插入数据。

1、线程A执行tryLock()方法成功获取锁，则把HashEntry对象插入到相应的位置；
2、线程B获取锁失败，则执行scanAndLockForPut()方法，在scanAndLockForPut方法中，会通过重复执行tryLock()方法尝试获取锁，
在多处理器环境下，重复次数为64，单处理器重复次数为1，当执行tryLock()方法的次数超过上限时，则执行lock()方法挂起线程B(放入AQS队列)；

3、当线程A执行完插入操作时，会通过unlock()方法释放锁，接着唤醒线程B继续执行；

---
ConcurrentHashMap的synchronized用来锁住链表头结点：
synchronized一定是链表的头结点，即该元素在Node数组中。所以这里锁住的是hash冲突那条链表。
---

————————————————  
JDK 1.8的实现降低锁的颗粒度，JDK 1.7版本的锁的颗粒度是基于Segment，包含多个HashEntry；而JDK 1.8的锁的颗粒度就是HashEntry。
JDK 1.8版本的数据结构变得更加简单，使得操作也更加清晰。使用了synchronized来进行同步，不需要分段锁的概念，也就不再需要Segment这种数据结构，由于颗粒度的降低，实现的复杂度也增加了。
JDK 1,8使用红黑树来优化链表，红黑树的遍历速度是很快的，代替了一定阈值的链表。  
————————————————


```Put方法分析：
sizeCtl：-1 代表初始化，0代表默认状态，-（1+其他正在进行扩容操作的线程数），初始化过后，代表在此resize时map中的元素个数

在初始化的逻辑中进行如下逻辑判断首先判断sizeCtl是否小于0，如果为true，则代表在多线程条件下，已经有线程在进行初始化工作，
则当前线程执行yield方法，让度出自己的资源，直到sizeCtl在其他线程初始化完成后被置成0，
则不满足while方法的判断条件，跳出循环.
如果为false，代表当前线程可以对table进行初始化，则利用CAS操作将当前的sc（即sizeCtl）置为-1，进入初始化的逻辑操作
n代表数组长度，由于sizeCtl为-1，所以n取为DEFAULT_CAPACITY=16, sc=sizeCtl更新为0，初始化完成


最后else：真正执行元素的添加操作，
非首节点，采用了synchronized首元素的方法来保证线程安全，
并且ConcurrentHashMap的底层采用的还是红黑树这种数据结构，
```



