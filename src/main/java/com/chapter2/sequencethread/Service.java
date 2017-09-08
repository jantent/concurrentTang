package com.chapter2.sequencethread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {

	private static volatile int nextThread = 1;
	private ReentrantLock lock = new ReentrantLock();
	Condition conditionA = lock.newCondition();
	Condition conditionB = lock.newCondition();
	Condition conditionC = lock.newCondition();

	public void excuteA() {
		try {
			lock.lock();
			while (nextThread != 1) {
				conditionA.await();
			}
			System.out.println(Thread.currentThread().getName() + " 工作");
			/**
			 * 由此可见 默认构造方法生成的是非公平锁
			 */
			System.err.println("是否是公平锁： "+lock.isFair());
			nextThread = 2;
			conditionB.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void excuteB() {
		try {
			lock.lock();
			while (nextThread != 2) {
				conditionB.await();
			}
			System.out.println(Thread.currentThread().getName() + " 工作");
			nextThread = 3;
			conditionC.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void excuteC() {
		try {
			lock.lock();
			while (nextThread != 3) {
				conditionC.await();
			}
			System.out.println(Thread.currentThread().getName() + " 工作");
			nextThread = 1;
			conditionA.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
