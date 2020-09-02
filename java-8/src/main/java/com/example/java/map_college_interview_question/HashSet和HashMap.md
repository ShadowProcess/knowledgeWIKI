/**
* HashSet构造方法其实是new HashMap()
*
* private static final Object PRESENT = new Object();
*
* public boolean add(E e) {
*    return map.put(e, PRESENT)==null;
* }
*
* HashSet将数据保存在HashMap的key的位置,而value位置保存的是一个空对象
*
* 在HashSet中，基本的操作都是有HashMap底层实现的，因为HashSet底层是用HashMap存储数据的。
* 当向HashSet中添加元素的时候，首先计算元素的hashcode值，
*/

/**
 * 使用HashSet 为什么要重写hashcode()和equals() 方法
 * Object.hashCode() 和 equals() 方法的用途
 * hashCode()方法和equals()方法的作用其实一样，在Java里都是用来对比两个对象是否相等一致。
 *
 * hashcode 是对象物理地址(或者跟对象地址值有关系的,不同jvm可能不一样)的整数值，equals() 方法默认比较的就是两个对象的地址是否相同；所以equals() 相等的两个对象，hashCode 一定相同；
 *
 * 下边从两个角度介绍了他们的区别：一个是性能，一个是可靠性。他们之间的主要区别也基本体现在这里。
 *
 * 1、equals()既然已经能实现对比的功能了，为什么还要hashCode()呢？
 * 因为重写的equals（）里一般比较的比较全面比较复杂，这样效率就比较低，而利用hashCode()进行对比，则只要生成一个hash值进行比较就可以了，效率很高。
 *
 * 2、hashCode()既然效率这么高为什么还要equals()呢？
 * 因为hashCode()并不是完全可靠，有时候不同的对象他们生成的hashcode也会一样（生成hash值得公式可能存在的问题），
 * 所以hashCode()只能说是大部分时候可靠，并不是绝对可靠，所以我们可以得出
 * （PS：以下两条结论是重点，很多人面试的时候都说不出来）：
 * equals()相等的两个对象他们的hashCode()肯定相等，也就是用equals()对比是绝对可靠的。但是效率低
 * hashCode()相等的两个对象他们的equals()不一定相等，也就是hashCode()不是绝对可靠的。
 *
 *
 * hashCode 和equals 方法的重写原则
 * hashCode和equals 1.只要重写equals,就必须重写hashCode 2.因为Set存储的是不重复的对象(根据hashCode和equals判断),
 * 所以Set储存的对象必须重写这两个方法 3.如果自定义对象作为Map的Key,必须重写hashCode和equals
 *
 */