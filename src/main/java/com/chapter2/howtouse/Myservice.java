package com.chapter2.howtouse;

import java.util.concurrent.locks.ReentrantLock;
/**
 * 线程执行业务
 * 
 * @author tangj
 *
 */
public class Myservice {

	ReentrantLock lock = new ReentrantLock();

	public void servicMethod() {
		// 加锁
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "进入service");
			// 设置线程睡眠
			Thread.sleep(3000);
			for (int i = 0; i < 3; i++) {
				System.out.println("打印了: " + i);
			}
			System.out.println(Thread.currentThread().getName() + "退出service");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 别忘了关锁，不然其他的线程就进不来了
		lock.unlock();
	}

}
