package practice.threadsafehashmap;

import java.lang.reflect.Array;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This is very simple implementation of hashmap and definitely not the best way to implement if required to be used in production.
 *
 * Look into JAVA ConcurrentHashMap implementation.
 * */
public class ThreadSafeHashMap<K,V> {
    //General idea: get hash of key and covert it to index of array
    //store value of key at index retrieved in step 1
    private V[] hashArray;
    private int length=10000;

    private ReentrantReadWriteLock lock;
    private ReentrantReadWriteLock.WriteLock writeLock;
    private ReentrantReadWriteLock.ReadLock readLock;
    public ThreadSafeHashMap() {
        this.hashArray = (V[])new Object[length];
        lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
        readLock = lock.readLock();
    }

    /*public ThreadSafeHashMap(Class<V[]> clazz) {
        this.hashArray = clazz.cast(Array.newInstance(clazz.getComponentType(), length));
    }
    */

    private int getHashCode(K key) {
        return key.hashCode()%length;
    }
    public void put(K key, V value) {
        writeLock.lock();
        try {
            int hashCode = getHashCode(key);
            hashArray[hashCode] = value;
        } finally {
            writeLock.unlock();
        }
    }

    //return null if key is not present
    public V get(K key) {
        readLock.lock();
        try {
            int hashCode = getHashCode(key);
            return hashArray[hashCode];
        } finally {
            readLock.unlock();
        }
    }

    public void remove(K key) {
        writeLock.lock();
        try {
            int hashcode = getHashCode(key);
            hashArray[hashcode] = null;
        } finally {
            writeLock.unlock();
        }
    }
}
