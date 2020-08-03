package com.example.juc.day01;

import java.util.concurrent.CountDownLatch;

/*
 * CountDownLatch ：闭锁，在完成某些运算是，只有其他所有线程的运算全部完成，当前运算才继续执行
 *
 * 例如：计算十个线程都执行完，用了多长时间
 */
public class TestCountDownLatch {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(50); //每个线程执行完成就将这个数减1，一直减为0，表示其它线程都执行完了
		LatchDemo ld = new LatchDemo(latch);

		long start = System.currentTimeMillis();

		for (int i = 0; i < 50; i++) {
			new Thread(ld).start();
		}

		try {
			//todo 会等待上面50个线程都结束，才执行
			latch.await();
		} catch (InterruptedException e) {
		}

		long end = System.currentTimeMillis();

		System.out.println("耗费时间为：" + (end - start));
	}

}





class LatchDemo implements Runnable {

	private CountDownLatch latch;

	public LatchDemo(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {

		try {
			for (int i = 0; i < 50000; i++) {
				if (i % 2 == 0) {
					System.out.println(i);
				}
			}
		} finally {
			//让CountDownLatch维护的计数器减一
			latch.countDown();
		}

	}

}