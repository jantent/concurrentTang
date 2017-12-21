package com.chapter3.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static  void main(String args[]){

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    System.out.println(Thread.currentThread().getId()+"执行了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },0,2, TimeUnit.SECONDS);
    }


}
