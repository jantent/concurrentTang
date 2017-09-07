package com.chapter2.howtouse;

public class MyServiceThread3 implements Runnable {

	private MyService myservice;

	public MyServiceThread3(MyService myservice) {
		this.myservice = myservice;
	}

	@Override
	public void run() {
		myservice.servicMethod();
	}

}
