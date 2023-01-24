package practice.producerconsumer.lockCondition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {
    private final Queue<E> queue;
    private final long maxSize;
    private final Lock lock;
    private final Condition isQueueFull;
    private final Condition isQueueEmpty;
    public MyBlockingQueue(int size) {
        System.out.println("Creating queue with size: " + size);
        queue = new LinkedList<>();
        this.maxSize = size;
        lock = new ReentrantLock();
        isQueueFull = lock.newCondition();
        isQueueEmpty = lock.newCondition();
    }
    public void addItem(E e) {
        lock.lock();
        try {
            if(queue.size() == maxSize) {
                isQueueFull.await();
            }
            queue.add(e);
            isQueueEmpty.signalAll();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } finally {
            lock.unlock();
        }
    }

    public E removeItem() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                isQueueEmpty.await();
            }
            E item = queue.remove();
            isQueueFull.signalAll();
            return item;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
