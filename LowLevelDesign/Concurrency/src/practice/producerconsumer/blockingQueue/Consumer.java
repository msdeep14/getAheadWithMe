package practice.producerconsumer.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<String> queue;
    Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String taskName = queue.take();
            //process task
            System.out.println("Consume and process task: " + taskName);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
