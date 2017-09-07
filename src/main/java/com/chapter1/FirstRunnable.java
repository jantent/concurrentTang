package com.chapter1;

public class FirstRunnable {

	public static void main(String[] args) {
		MyRunnable myRunnable;
		myRunnable = new MyRunnable();
		new Thread(myRunnable).start();
	}

}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Hello World!");
	}
}