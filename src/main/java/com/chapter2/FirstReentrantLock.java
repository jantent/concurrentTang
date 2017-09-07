package com.chapter2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FirstReentrantLock {

	public static void main(String[] args) {
		Runnable runnable = new ReentrantLockThread();
		new Thread(runnable,"a").start();
		new Thread(runnable,"b").start();
	}

}

class ReentrantLockThread implements Runnable{

	Lock lock = new ReentrantLock();
	@Override
	public void run() {
		lock.lock();
		for(int i = 0;i<3;i++){
			System.out.println(Thread.currentThread().getName()+"Êä³öÁË£º  "+i);
		}
		lock.unlock();
	} 
	
}