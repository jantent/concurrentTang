package com.interviewquestion.questiona;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题A：
 * 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 */
public class QuestionA {
    public static void main(String args[]) {
        ServiceTask serviceTask = new ServiceTask();
        Thread subThread = getSubThread(serviceTask);
        Thread mainThread =getMainThread(serviceTask);
        mainThread.start();
        subThread.start();
    }

    private static Thread getSubThread(final ServiceTask service) {
        return new Thread() {
            @Override
            public void run() {
                for (int i = 0;i<50;i++) {
                    service.subExecute();
                }
            }
        };
    }

    private static Thread getMainThread(final ServiceTask service) {
        return new Thread() {
            @Override
            public void run() {
                for (int i = 0;i<50;i++) {
                    service.mainExecute();
                }
            }
        };
    }

}

class ServiceTask {
    // 锁
    private ReentrantLock lock = new ReentrantLock();
    // 子线程conditon
    private Condition mainCond = lock.newCondition();
    // 主线程condition
    private Condition subCond = lock.newCondition();

    private int nextThread = 1;

    /**
     * 执行子线程任务
     */
    public void subExecute() {
        try {
            lock.lock();
            while (nextThread != 1) {
                subCond.await();
            }
            for (int i = 1; i <= 20; i++) {
                System.out.println("sub线程执行" + i);
            }
            nextThread = 2;
            mainCond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 执行主线程任务
    public void mainExecute() {
        try {
            lock.lock();
            while (nextThread != 2) {
                mainCond.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("main线程执行" + i);
            }
            nextThread = 1;
            subCond.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

