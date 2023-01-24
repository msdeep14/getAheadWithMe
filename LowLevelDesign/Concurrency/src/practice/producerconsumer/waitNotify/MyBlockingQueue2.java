package practice.producerconsumer.waitNotify;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue2<E> {
    private final Queue<E> queue;
    private final int maxSize;
    Object isQueueFull;
    Object isQueueEmpty;
    public MyBlockingQueue2(int size) {
        queue = new LinkedList<>();
        this.maxSize = size;
        isQueueEmpty = new Object();
        isQueueFull = new Object();
    }

    public synchronized void addItem(E e) {
        try {
            if(queue.size() == maxSize) {
                isQueueFull.wait();
            }
            queue.add(e);
            isQueueEmpty.notifyAll();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public synchronized E removeItem() {
        try {
            if(queue.size() == 0) {
                isQueueEmpty.wait();
            }
            E item = queue.remove();
            isQueueFull.notifyAll();
            return item;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
