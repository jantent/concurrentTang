package com.chapter2.howtouse;

public class MyServiceThread1 implements Runnable{

	private MyService myservice;
	
	public MyServiceThread1(MyService myservice) {
		this.myservice = myservice;
	}
	
	@Override
	public void run() {
		myservice.servicMethod();
	}

}
