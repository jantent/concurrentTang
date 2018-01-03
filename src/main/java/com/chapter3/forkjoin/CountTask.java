package com.chapter3.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long>{
    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */

    private static final int THRESHOLD = 10000;

    private long start;

    private long end;

    public CountTask(long start, long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end-start)<THRESHOLD;
        if (canCompute){
            for (long i=start;i<end;i++){
                sum+=i;
            }
        }else {
            //分成100个小人物
            long step = (start+end)/100;
            ArrayList<CountTask>subTasks = new ArrayList<CountTask>();
            long pos = start;
            for (int i=0;i<100;i++){
                long lastone = pos+step;
                if (lastone>end){
                    lastone = end;
                }
                CountTask subTask = new CountTask(pos,lastone);
                pos+=step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (CountTask t:subTasks){
                sum+=t.join();
            }
        }

        return sum;
    }

    public static void main(String [] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0,200000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try {
            long res = result.get();
            System.out.println("总数sum="+res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
