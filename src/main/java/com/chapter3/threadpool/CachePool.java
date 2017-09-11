package com.chapter3.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
 */
public class CachePool {
    private static Runnable getThread(final int i){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(i);
            }
        };
    }

    public static  void main(String args[]){
        ExecutorService cachePool = Executors.newCachedThreadPool();
        for (int i=1;i<=10;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachePool.execute(getThread(i));
        }
    }
}
