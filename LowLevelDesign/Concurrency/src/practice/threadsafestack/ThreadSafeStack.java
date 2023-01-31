package practice.threadsafestack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Implement thread-safe stack using a LinkedList.
 *  Push and pop should be O(1)
 * */
public class ThreadSafeStack<T> {
    private final Deque<T> dq;
    private final ReentrantLock lock;

    public ThreadSafeStack() {
        this.dq = new LinkedList<>();
        this.lock = new ReentrantLock();
        System.out.println("Stack created successfully!!!");
    }

    public void push(T item) {
        lock.lock();
        try {
            System.out.println("Adding item: " + item);
            dq.addFirst(item);
        } finally {
            lock.unlock();
        }
    }

    public void pop() {
        lock.lock();
        try {
            if(dq.size() == 0) {
                System.out.println("Stack is empty, no items to for pop");
            }
            System.out.println("Removing item " + dq.removeFirst());
        } finally {
            lock.unlock();
        }
    }
}
