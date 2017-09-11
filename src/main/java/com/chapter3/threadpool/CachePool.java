package com.chapter3.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
