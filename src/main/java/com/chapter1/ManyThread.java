package com.chapter1;

/**
 * 多个线程实例
 * 
 * @author tangj
 *
 */
public class ManyThread {

	public static void main(String args[]) {
		Runnable runnable = new MyRunnable2();
		new Thread(runnable, "a").start();
		new Thread(runnable, "b").start();
		new Thread(runnable, "c").start();
		new Thread(runnable, "d").start();
	}
}

class MyRunnable2 implements Runnable {

	int count = 0;

	// 注意在这里增加了关键字 synchronized
	@Override
	synchronized public void run() {
		autoIncrement();
		System.out.println(Thread.currentThread().getName() + "计算了" + "count:" + count);
	}

	// 执行自增操作
	private void autoIncrement() {
		count++;
	}
}
