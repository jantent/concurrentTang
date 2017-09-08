package com.chapter2.consumeone;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Service {

	private Lock lock = new ReentrantLock();
	private boolean flag = false;
	private Condition condition = lock.newCondition();

	public void produce() {
		try {
			lock.lock();
			while (flag == true) {
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "-----生产-----");
			System.out.println();
			flag = true;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void consume() {
		try {
			lock.lock();
			while (flag == false) {
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "-----消费-----");
			System.out.println();
			flag = false;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
