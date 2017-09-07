package com.chapter2.howtouse;

public class MyServiceThread1 implements Runnable{

	private Myservice myservice;
	
	public MyServiceThread1(Myservice myservice) {
		this.myservice = myservice;
	}
	
	@Override
	public void run() {
		myservice.servicMethod();
	}

}
