package practice.producerconsumer.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<String> queue;
    private final String taskName;
    Producer(BlockingQueue<String> queue, String taskName) {
        this.queue = queue;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Adding task: " + taskName + " to the queue");
        queue.add(taskName);
    }
}
