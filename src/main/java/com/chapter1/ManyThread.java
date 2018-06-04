package com.chapter1;

/**
 *
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
	@Override
	synchronized public void run() {
		autoIncrement();
		System.out.println(Thread.currentThread().getName()  + "count:" + count);
	}

	private void autoIncrement() {
		count++;
	}
}
