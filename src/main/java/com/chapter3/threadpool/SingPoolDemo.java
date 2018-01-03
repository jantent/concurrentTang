package com.chapter3.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingPoolDemo {
    private static Runnable getThread(final int i){
        return new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        };
    }

    public static void main(String args[]){
        ExecutorService singPool = Executors.newSingleThreadExecutor();
        for (int i=0;i<10;i++){
            singPool.execute(getThread(i));
        }
        singPool.shutdown();
    }
}
