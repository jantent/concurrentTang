package com.chapter2.consumeone;

/**
 * 生产者线程
 * 
 * @author tangj
 *
 */
public class MyThreadService1 implements Runnable {

	private Service service;

	public MyThreadService1(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		for (;;) {
			service.produce();
		}
	}

}
