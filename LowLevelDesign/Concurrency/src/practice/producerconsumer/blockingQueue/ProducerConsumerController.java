package practice.producerconsumer.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerController {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        // produce tasks
        new Thread(new Producer(queue, "task1")).start();
        new Thread(new Producer(queue, "task2")).start();

        //consume tasks
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();

        // below code will wait for new tasks to be added to be queue as there are no tasks in the queue.
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}
