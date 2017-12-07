package com.chapter5;

import java.util.concurrent.*;

/**
 * @author tangj
 */
public class TestBlockingQueue {

    protected class WorkDesk {

        BlockingQueue<String> desk = new LinkedBlockingQueue<String>(10);

        public void washDish() throws InterruptedException {
            desk.put("洗好一个盘子");
        }

        public String useDish() throws InterruptedException {
            return desk.take();
        }
    }

    class Producer implements Runnable {

        private String producerName;
        private WorkDesk workDesk;

        public Producer(String producerName, WorkDesk workDesk) {
            this.producerName = producerName;
            this.workDesk = workDesk;
        }

        @Override
        public void run() {
            try {
                for (; ; ) {
                    System.out.println(producerName + "洗好一个盘子");
                    workDesk.washDish();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        private String consumerName;
        private WorkDesk workDesk;

        public Consumer(String consumerName, WorkDesk workDesk) {
            this.consumerName = consumerName;
            this.workDesk = workDesk;
        }

        @Override
        public void run() {
            try {
                for (; ; ) {
                    System.out.println(consumerName + "使用一个盘子");
                    workDesk.useDish();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        TestBlockingQueue testQueue = new TestBlockingQueue();
        WorkDesk workDesk = testQueue.new WorkDesk();

        ExecutorService service = Executors.newCachedThreadPool();

        Producer producer1 = testQueue.new Producer("生产中-2-",workDesk);
        Producer producer2 = testQueue.new Producer("生产者-2-",workDesk);
        Consumer consumer = testQueue.new Consumer("消费者-1-",workDesk);

        service.submit(producer1);
        service.submit(producer2);
        service.submit(consumer);

    }
}
