package com.chapter2.howtouse;

public class MyServiceThread3 implements Runnable {

	private Myservice myservice;

	public MyServiceThread3(Myservice myservice) {
		this.myservice = myservice;
	}

	@Override
	public void run() {
		myservice.servicMethod();
	}

}
