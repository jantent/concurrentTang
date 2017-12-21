package com.chapter4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParseDate implements Runnable{

    private static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();

    int i=0;
    public ParseDate(int i){
        this.i= i;
    }

    @Override
    public void run() {
        try {
            if (tl.get() == null){
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            Date t = tl.get().parse("2017-12-17 19:29:"+i%60);
            System.out.println(i+":"+t);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<1000;i++){
            executorService.execute(new ParseDate(i));
        }
        executorService.shutdown();
    }
}
