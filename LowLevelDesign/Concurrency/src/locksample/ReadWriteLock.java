package locksample;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private void readState() {
        readLock.lock();
        try {
            //read state
        } finally {
            readLock.unlock();
        }
    }

    private void writeState() {
        writeLock.lock();
        try {
            //write state
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReadWriteLock();
        Thread t1 = new Thread(readWriteLock::readState);
        t1.start();
        Thread t2 = new Thread(readWriteLock::readState);
        t2.start();
        Thread t3 = new Thread(readWriteLock::writeState);
        t3.start();
        Thread t4 = new Thread(readWriteLock::writeState);
        t4.start();
    }
}
