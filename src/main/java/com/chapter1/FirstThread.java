package com.chapter1;

public class FirstThread {

    public static void main(String[] args) {
        InnerThread thread = new InnerThread();
        thread.start();
    }

}

class InnerThread extends Thread {


    @Override
    public void run() {
        super.run();
        System.out.println("Hello World!");
    }
}