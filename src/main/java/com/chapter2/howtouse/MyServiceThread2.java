package com.chapter2.howtouse;

public class MyServiceThread2 implements Runnable {

	private MyService myservice;

	public MyServiceThread2(MyService myservice) {
		this.myservice = myservice;
	}

	@Override
	public void run() {
		myservice.servicMethod();
	}

}
