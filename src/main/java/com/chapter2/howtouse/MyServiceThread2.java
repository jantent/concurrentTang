package com.chapter2.howtouse;

public class MyServiceThread2 implements Runnable {

	private Myservice myservice;

	public MyServiceThread2(Myservice myservice) {
		this.myservice = myservice;
	}

	@Override
	public void run() {
		myservice.servicMethod();
	}

}
