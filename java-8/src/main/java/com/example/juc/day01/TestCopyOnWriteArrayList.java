package com.example.juc.day01;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * CopyOnWriteArrayList/CopyOnWriteArraySet : “写入并复制”
 * 注意：添加操作多时，效率低，因为每次添加时都会进行复制，开销非常的大。【并发迭代操作多时可以选择。】
 *
 * 优点:
	对于一些读多写少的数据，这种做法的确很不错，例如配置、黑名单、物流地址等变化非常少的数据，这是一种无锁的实现。可以帮我们实现程序更高的并发。
   缺点:
	这种实现只是保证数据的最终一致性，在添加到拷贝数据而还没进行替换的时候，读到的仍然是旧数据。如果对象比较大，频繁地进行替换会消耗内存，从而引发Java的GC问题，这个时候，我们应该考虑其他的容器，例如ConcurrentHashMap。
 */

/**
 * Copy-On-Write，写入时复制，这个技术，准确的说应该是一种思想，在很多系统设计上都会用到，今天我们来谈一谈Java语言中，
 * JDK运用这种写入时复制的思想的数据结构/容器，CopyOnWriteArrayList。CopyOnWriteArrayList，是一个写入时复制的容器，它是如何工作的呢？
 * 简单来说，就是平时查询的时候，都不需要加锁，随便访问，只有在写入/删除的时候，才会从原来的数据复制一个副本出来，然后修改这个副本，
 * 最后把原数据替换成当前的副本。修改操作的同时，读操作不会被阻塞，而是继续读取旧的数据。这点要跟读写锁区分一下。
 */
public class TestCopyOnWriteArrayList {

	public static void main(String[] args) {
		HelloThread ht = new HelloThread();
		
		for (int i = 0; i < 10; i++) {
			new Thread(ht).start();
		}
	}
}




class HelloThread implements Runnable{
	
//	private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
	
	private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
	
	static{
		list.add("AA");
		list.add("BB");
		list.add("CC");
	}

	@Override
	public void run() {
		
		Iterator<String> it = list.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
			
			list.add("AA"); //不使用CopyOnWriteArrayList，那么这个操作将报并发修改异常
		}
		
	}
	
}