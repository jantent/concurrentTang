package com.chapter2.howtocondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private ReentrantLock lock = new ReentrantLock();
	public Condition conditionA = lock.newCondition();
	public Condition conditionB = lock.newCondition();

	public void awaitA() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "获得锁A");
			long timeBefore = System.currentTimeMillis();
			conditionA.await();
			long timeAfter = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName() + "持有锁的时间 " + (timeAfter - timeBefore)/1000+"s");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void awaitB() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "获得锁B");
			long timeBefore = System.currentTimeMillis();
			conditionB.await();
			long timeAfter = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName() + "持有锁的时间 " + (timeAfter - timeBefore)/1000+"s");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void signallA() {
		try {
			lock.lock();
			System.out.println("唤醒A");
			conditionA.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void signallB() {
		try {
			lock.lock();
			System.out.println("唤醒B");
			conditionB.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
