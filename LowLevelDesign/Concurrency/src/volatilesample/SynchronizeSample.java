package volatilesample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizeSample {

    private long count = 0;

    public synchronized void incrementCounter() {
        count = getCount() + 1;

        /**
         * synchronized block can also be applied on specific block of code such as --
         *
         * `this` object in synchronized block is monitor object and code is synchronized on this object.
         * For static method this would be - synchronized(SynchronizeSample.class)
         *
         * synchronized (this) {
         *    count = getCount() + 1;
         * }
         *
         * The current thread can acquire same synchronized lock again and again while holding it. - reentrant locks.
         *
         * */
    }

    public synchronized long getCount() {
        return count;
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        SynchronizeSample synchronizeSample = new SynchronizeSample();

        for(int i=0;i<1000;i++) {
            executorService.submit(synchronizeSample::incrementCounter);
        }
        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);

        /**
         * Output without synchronized keyword is inconsistent.
         * Try to run the main() method multiple times, you'll get different output, I got 1000, 997, etc.
         *
         * Modify method signature as public synchronized void incrementCounter() {}, and you'll get consistent output as 1000.
         * */
        System.out.println("Count value is : " + synchronizeSample.getCount());
    }
}
