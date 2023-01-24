package practice.producerconsumer.lockCondition;

public class Producer2 implements Runnable {
    private final MyBlockingQueue<String> queue;
    private final String taskName;
    public Producer2(MyBlockingQueue<String> queue, String taskName) {
        this.queue = queue;
        this.taskName = taskName;
    }
    @Override
    public void run() {
        System.out.println("Adding task - " + taskName + " to the queue");
        queue.addItem(taskName);
    }
}
