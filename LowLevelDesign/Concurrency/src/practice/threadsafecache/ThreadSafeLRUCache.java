package practice.threadsafecache;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeLRUCache<K,V> {
    private final Deque<K> dq;
    private final HashMap<K,V> map;
    private final int capacity;
    private final ReentrantReadWriteLock lock;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;

    public ThreadSafeLRUCache(int capacity) {
        System.out.println("Creating LRUCache with capacity: " + capacity);
        this.dq = new LinkedList<>();
        this.map = new HashMap<>();
        this.capacity = capacity;
        lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    /**
     * Return value of key if present in LRUCache
     * else null
     * */
    public V get(K key) {
        readLock.lock();
        try {
            V value = null;
            if(map.containsKey(key)) {
                value = map.get(key);
            }
            return value;
        } finally {
            readLock.unlock();
        }
    }

    public void put(K key, V value) {
        writeLock.lock();
        try {
            if(!map.containsKey(key)) {
                if(dq.size() == capacity) {
                    K last = dq.removeLast();
                    map.remove(last);
                }
            } else {
                dq.remove(key);
            }
            dq.push(key);
            map.put(key,value);
        } finally {
            writeLock.unlock();
        }
    }
}
