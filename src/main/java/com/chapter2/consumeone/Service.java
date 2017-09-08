package com.chapter2.consumeone;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Service {

	private Lock lock = new ReentrantLock();
	private boolean flag = false;
	private Condition condition = lock.newCondition();
	// 以此为衡量标志
	private int number = 1;

	/**
	 * 生产者生产
	 */
	public void produce() {
		try {
			lock.lock();
			while (flag == true) {
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "-----生产-----");
			number++;
			System.out.println("number: " + number);
			System.out.println();
			flag = true;
			// 提醒消费者消费
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 消费者消费生产的物品
	 */
	public void consume() {
		try {
			lock.lock();
			while (flag == false) {
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "-----消费-----");
			number--;
			System.out.println("number: " + number);
			System.out.println();
			flag = false;
			// 提醒生产者生产
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
