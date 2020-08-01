package com.example.juc.day02;

/**
 * 生产者和消费者案例
 * 等待唤醒机制
 *
 *  wait()
 * 导致当前线程等待，直到其他线程调用notify()方法或notifyAll()此对象的方法。
 * 换句话说，此方法的行为就好像它简单地执行呼叫wait(0)
 * 当前线程必须拥有该对象的监视器。 这款显示器并等待线程释放所有权，
 * 直到另一个线程通知等候在这个对象监视器上的通过调用要么醒来的notify方法或notifyAll方法。
 * 该线程将等到重新获得对监视器的所有权后才能继续执行。
 *
 * 	wait()方法表示，放弃当前对资源的占有权，等啊等啊，一直等到有人通知我，我才会运行后面的代码。
 *
 *	notify()方法
 *  它只是选择一个wait状态线程进行通知，并使它获得该对象上的锁，
 *  但不惊动其他同样在等待被该对象notify的线程，
 *  当第一个线程运行完毕以后释放对象上的锁此时如果该对象没有再次使用notify语句，
 *  则即便该对象已经空闲，其他wait状态等待的线程由于没有得到该对象的通知，继续处在wait状态，
 *  直到这个对象发出一个notify或notifyAll，它们等待的是被notify或notifyAll，而不是锁。
 *
 *	notifyAll()方法表示，
 *	使所有原来在该对象上等待被notify的所有线程统统退出wait的状态，
 *	变成等待该对象上的锁，一旦该对象被解锁，他们就会去竞争。
 */

public class TestProductorAndConsumer {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		
		Productor pro = new Productor(clerk);
		Consumer cus = new Consumer(clerk);
		
		new Thread(pro, "生产者 A").start();
		new Thread(cus, "消费者 B").start();
		
		new Thread(pro, "生产者 C").start();
		new Thread(cus, "消费者 D").start();
	}
	
}

/*//店员
class Clerk{
	private int product = 0;
	
	//进货
	public synchronized void get(){//循环次数：0
		while(product >= 1){//为了避免虚假唤醒问题，应该总是使用在循环中
			System.out.println("产品已满！");
			
			try {
				this.wait(); //等待，并释放锁资源
			} catch (InterruptedException e) {
			}
			
		}
		
		System.out.println(Thread.currentThread().getName() + " : " + ++product);
		this.notifyAll(); //唤醒
	}
	
	//卖货
	public synchronized void sale(){//product = 0; 循环次数：0
		while(product <= 0){
			System.out.println("缺货！");
			
			try {
				this.wait(); //等待，并释放锁资源
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println(Thread.currentThread().getName() + " : " + --product);
		this.notifyAll(); //唤醒
	}
}

//生产者
class Productor implements Runnable{
	private Clerk clerk;

	public Productor(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			
			clerk.get();
		}
	}
}

//消费者
class Consumer implements Runnable{
	private Clerk clerk;

	public Consumer(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			clerk.sale();
		}
	}
}*/