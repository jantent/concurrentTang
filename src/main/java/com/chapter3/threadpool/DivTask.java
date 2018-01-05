package com.chapter3.threadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable{
    int a,b;
    public DivTask(int a,int b){
        this.a = a;
        this.b = b;
    }
    @Override
    public void run() {
        double re = a/b;
        System.out.println(re);
    }

    public static void main(String[] args){
        ThreadPoolExecutor ex = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L, TimeUnit.MICROSECONDS,new SynchronousQueue<Runnable>());
        for (int i=0;i<5;i++){
            ex.execute(new DivTask(100,i));
        }
    }
}
