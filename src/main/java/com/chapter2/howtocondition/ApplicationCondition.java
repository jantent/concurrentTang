package com.chapter2.howtocondition;

public class ApplicationCondition {

	public static void main(String[] args) throws InterruptedException {
		MyService service = new MyService();
		Runnable runnable1 = new MyServiceThread1(service);
		Runnable runnable2 = new MyServiceThread2(service);
		
		new Thread(runnable1, "a").start();
		new Thread(runnable2, "b").start();
		
		// 线程sleep2秒钟
		Thread.sleep(2000);
		// 唤醒所有持有conditionA的线程
		service.signallA();
		
		Thread.sleep(2000);
		// 唤醒所有持有conditionB的线程
		service.signallB();
	}

}
