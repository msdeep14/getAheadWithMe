package practice.threadsafequeue;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Create a queue which is thread-safe.
 * Thread-safe? - provides thread safety on concurrent task execution. In simple terms, safely perform queue operations
 * like enque without worrying about unwanted outputs or race conditions.
 *
 * If the queue is thread safe, programmer don't have to write explicit logic to handle multiple threads in applications
 * while accessing queue data structure.
 *
 *
 *
 * */
public class ThreadSafeQueue<T> {

    int capacity;
    LinkedList<T> list;
    ReentrantReadWriteLock readWriteLock;
    ReentrantReadWriteLock.ReadLock readLock;
    ReentrantReadWriteLock.WriteLock writeLock;

    public ThreadSafeQueue() {
        capacity = 0;
        list = new LinkedList<>();
        readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    public void enqueue(T value) {
        //insert value in queue
       writeLock.lock();
       try {
           list.add(value);
       } finally {
           writeLock.unlock();
       }
    }

    public void dequeue() {
        //remove value from queue - follow FIFO
        writeLock.lock();
        try {
            list.remove();
        } finally {
            writeLock.unlock();
        }
    }

    public T getFirst() {
        readLock.lock();
        T ans;
        try {
            ans = list.getFirst();
            return ans;
        } finally {
            readLock.unlock();
        }
    }

    public T getLast() {
        readLock.lock();
        try {
            return list.getLast();
        } finally {
            readLock.unlock();
        }
    }

    public void printQueue() {
        //iterate over queue and print all elements
        for (T t : list) {
            System.out.println(t);
        }
    }

}
