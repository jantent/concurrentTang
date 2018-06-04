package com.chapter1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ManyThread2 {

	public static void main(String[] args) {
		Runnable runnable = new MyRunnable3();
		new Thread(runnable, "a").start();
		new Thread(runnable, "b").start();
		new Thread(runnable, "c").start();
		new Thread(runnable, "d").start();
	}
}

class MyRunnable3 implements Runnable{
	
	int count = 0;

	Lock lock = new ReentrantLock();
	
	@Override
	public void run() {
		lock.lock();
		count++;
		System.out.println(Thread.currentThread().getName()  + "count:" + count);

		lock.unlock();
	}
	
}