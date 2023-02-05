package practice.producerconsumer.lockCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerController2 {

    public static void main(String[] args) {
        MyBlockingQueue<String> myBlockingQueue = new MyBlockingQueue<>(10);

        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(new Producer2(myBlockingQueue, "myTask1"));
        service.submit(new Producer2(myBlockingQueue, "myTask2"));
        service.submit(new Consumer2(myBlockingQueue));
        service.submit(new Consumer2(myBlockingQueue));
        service.submit(new Consumer2(myBlockingQueue));

        service.shutdown();
    }
}
