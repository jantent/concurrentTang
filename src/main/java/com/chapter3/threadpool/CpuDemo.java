package com.chapter3.threadpool;

public class CpuDemo {
    public static void main(String args[]){
        int ncpus = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU数量"+ncpus);
    }
}
