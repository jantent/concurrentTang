package com.chapter4;

import java.util.concurrent.*;

public class Demo1 {

    public static void main(String args[]) throws Exception {
        // 1.先实例化任务对象
        ServiceTask task = new ServiceTask();
        // 2.实例化Executor框架中的线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        // 3.使用submit方法将任务提交（返回的是一个Future)
        Future<Integer> result = executor.submit(task);
        // 4.记得关闭线程池
        executor.shutdown();
        System.out.println("正在执行任务");
        Thread.sleep(1000);
        // 5.设置定时一秒取结果
        System.out.println("task运行结果为:" + result.get(1,TimeUnit.MILLISECONDS));
    }
}

/**
 * Callable的实现类
 */
class ServiceTask implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        //这里睡眠2秒
        Thread.sleep(2000);
        int result = 0;
        // 假设一个很庞大的计算
        for(int i=1;i<100;i++){
            for (int j=0;j<i;j++){
                result +=j;
            }
        }
        return result;
    }
}