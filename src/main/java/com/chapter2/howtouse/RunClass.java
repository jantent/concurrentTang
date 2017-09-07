package com.chapter2.howtouse;

public class RunClass {

	public static void main(String[] args) {
		MyService myservice = new MyService();
		Runnable runnable1 = new MyServiceThread1(myservice);
		Runnable runnable2 = new MyServiceThread2(myservice);
		Runnable runnable3 = new MyServiceThread3(myservice);

		new Thread(runnable1, "a").start();
		new Thread(runnable2, "b").start();
		new Thread(runnable3, "c").start();
	}

}
