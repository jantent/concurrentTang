package com.chapter4;

import java.util.concurrent.*;

public class FutureCallDemo2 {

    public static void main(String args[])throws  Exception{
        ExecutorService excutor = Executors.newCachedThreadPool();
        MyTask1 task = new MyTask1();
        FutureTask<Long> result = new FutureTask<Long>(task);
        excutor.submit(result);
        Thread.sleep(1000);
        System.out.println("主线程正在执行任务");
        //超时的haul抛出TimeoutException
        System.out.println("task运行结果为:" + result.get(10000, TimeUnit.MILLISECONDS));
    }
}

class MyTask1 implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        Thread.sleep(3000);
        long sum = 1;
        for (int i = 1; i <= 10; i++) {
            sum = sum * i;
        }
        return sum;
    }
}