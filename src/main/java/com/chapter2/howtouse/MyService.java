package com.chapter2.howtouse;

import java.util.concurrent.locks.ReentrantLock;
/**
 * @author tangj
 *
 */
public class MyService {

	ReentrantLock lock = new ReentrantLock();

	public void servicMethod() {

		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "进入service");
			Thread.sleep(3000);
			for (int i = 0; i < 3; i++) {
				System.out.println("执行: " + i);
			}
			System.out.println(Thread.currentThread().getName() + "完成service");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}

}
