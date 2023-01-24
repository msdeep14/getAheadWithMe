package locksample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is re-implementation of {@link volatilesample.SynchronizeSample} using locks.
 * */
public class LockSample {
    private long count = 0;

    private final Lock lock = new ReentrantLock();

    public void incrementCounter() {
        lock.lock();
        try {
            count = getCount() + 1;
        } finally {
            lock.unlock();
        }

        /*boolean isLockAvailable = lock.tryLock();
        //wait for 1 second if lock can be acquired, if not then do non-critical stuff in else block.
        boolean isLockAvailable2 = lock.tryLock(1000, TimeUnit.MILLISECONDS);
        if(isLockAvailable) {
            try {
                // access critical section
            } finally {
                lock.unlock();
            }
        } else {
            // do some non-critical section stuff
            // This way thread is not in blocked state.
        }*/
    }

    public long getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        LockSample lockSample = new LockSample();
        for(int i=0;i<1000;i++) {
            service.submit(lockSample::incrementCounter);
        }
        System.out.println("Counter value is : " + lockSample.getCount());
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        service.shutdown();
    }
}
