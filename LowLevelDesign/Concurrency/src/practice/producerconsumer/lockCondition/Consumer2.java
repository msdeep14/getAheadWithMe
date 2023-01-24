package practice.producerconsumer.lockCondition;

public class Consumer2 implements Runnable {

    private final MyBlockingQueue<String> queue;

    public Consumer2(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String item = queue.removeItem();
        System.out.println("task - " + item + " removed from the queue for processing");
    }
}
