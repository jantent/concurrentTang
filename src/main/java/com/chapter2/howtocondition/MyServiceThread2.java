package com.chapter2.howtocondition;

public class MyServiceThread2 implements Runnable {

	private MyService service;

	public MyServiceThread2(MyService service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.awaitB();
	}

}
