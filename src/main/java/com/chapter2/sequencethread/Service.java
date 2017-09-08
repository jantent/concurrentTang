package com.chapter2.sequencethread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {

	// 通过nextThread控制下一个执行的线程
	private static int nextThread = 1;
	private ReentrantLock lock = new ReentrantLock();
	// 有三个线程，所有注册三个Condition
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
