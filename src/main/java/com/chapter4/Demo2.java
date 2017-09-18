package com.chapter4;

import com.util.FileUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo2 {

    private static String filePath = "src/main/resource/file/callable.txt";

    public static void main(String args[]) throws Exception {
        FileService task = new FileService(filePath);
        ExecutorService excutor = Executors.newCachedThreadPool();
        Future<String> future = excutor.submit(task);
        excutor.shutdown();
        System.out.println("线程正在执行");
        Thread.sleep(3000);
        System.out.println("执行结果为： ");
        System.out.println(future.get());
    }
}

class FileService implements Callable<String> {

    private String path;

    public FileService(String path) {
        this.path = path;
    }

    private FileService() {
    }

    @Override
    public String call() throws Exception {
        String data = FileUtil.readFileAsRows(path,1000);
        return data;
    }
}
