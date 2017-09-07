package com.chapter1;

public class FirstThread {

	public static void main(String[] args) {
		InnerThread thread = new InnerThread();
		// 线程启动
		thread.start();
	}

}

class InnerThread extends Thread {

	// Override run方法，写入自己想要实现的业务
	@Override
	public void run() {
		super.run();
		System.out.println("Hello World!");
	}
}