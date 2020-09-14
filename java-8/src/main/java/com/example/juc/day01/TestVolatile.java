package com.example.juc.day01;

/*
 * 一、volatile 关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见。
 * 					  相较于 synchronized 是一种较为轻量级的同步策略。
 * 					  限制jvm的指令重排序
 * 
 * 注意：
 * 1. volatile 不具备“互斥性”
 * 2. volatile 不能保证变量的“原子性”
 */
public class TestVolatile {
	
	public static void main(String[] args) {
		//A-这个线程去修改值
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();

		//B-这个主线程去读取值
		while(true){
			if(td.isFlag()){
				System.out.println("------------------");
				break;
			}
		}
	}
}


///写一个线程
class ThreadDemo implements Runnable {

	private volatile boolean flag = false;

	@Override
	public void run() {
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		flag = true;
		System.out.println("flag=" + isFlag());
	}

	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}