package com.chapter3.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledPoolDemo {
    private static Runnable getRunnable(final int i){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(i);
            }
        };
    }

    public static void main(String args[]){
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(5);
        scheduledPool.scheduleAtFixedRate(getRunnable(89),1,2, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledPool.shutdown();
    }
}
