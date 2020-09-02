#HashMap 不是线程安全的  会导致环链，CPU利用率接近100%
>HashMap是map接口的实现类，是将键映射到值的对象，其中键和值都是对象，并且不能包含重复键，但可以包含重复值。
>HashMap允许有一个key值为null，并且允许（任意多的）value值为null

#HashTable 是线程安全 Collection。
>HashMap是HashTable的轻量级实现，他们都完成了Map接口，由于非线程安全，效率上高于Hashtable。
>而Hashtable不允许key为null，也不允许value为null

区别如下：
>HashMap允许将 null 作为一个entry 的 key 或者 value，而Hashtable不允许。
>HashMap 把Hashtable的 contains 方法去掉了，改成 containsValue 和 containsKey。因为 contains 方法容易让人引起误解。
>HashTable 的方法是 Synchronize 的，而HashMap不是，在多个线程访问Hashtable时，不需要自己为它的方法实现同步，而 HashMap 就必须为之提供外同步。
>Hashtable 和 HashMap 采用的 hash/rehash 算法都大概一样，所以性能不会有很大的差异。
