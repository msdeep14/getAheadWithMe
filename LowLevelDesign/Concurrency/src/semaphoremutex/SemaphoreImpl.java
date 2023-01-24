package semaphoremutex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreImpl {
    static class SampleTask implements Runnable {
        Semaphore semaphore;
        int taskNumber;
        public SampleTask(Semaphore semaphore, int taskNumber) {
            this.semaphore = semaphore;
            this.taskNumber = taskNumber;
        }
        @Override
        public void run() {
            System.out.println("Staring SampleTask " + taskNumber);
            try {
                semaphore.acquire();
                System.out.println("Semaphore acquired for task " + taskNumber);
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //Semaphore as permits=1 can act as mutex lock.
        final Semaphore semaphore = new Semaphore(5);
        // fair=true ensures long waiting thread is given permit first.
        final Semaphore semaphore2 = new Semaphore(5, true);

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for(int i=0;i<100;i++) {
//            executorService.execute(new SampleTask(semaphore, i));
//        }
//
//        executorService.shutdown();
//        executorService.awaitTermination(200, TimeUnit.MILLISECONDS);


        /**
         * semaphore3.acquire() will reach to blocked state as there are no available permits.
         * */
        /*Semaphore semaphore3 = new Semaphore(0);
        System.out.println(semaphore3.availablePermits());
        semaphore3.acquire();
        System.out.println(semaphore3.availablePermits());*/

        /**
         * The semaphore initialized with zero permits
         * but semaphore.release() will add new permits.
         *
         * useful for problems similar to https://leetcode.com/problems/print-zero-even-odd/description/
         * */
        Semaphore semaphore4 = new Semaphore(0);
        System.out.println(semaphore4.availablePermits());
        semaphore4.release();
        System.out.println(semaphore4.availablePermits());
        semaphore4.release();
        System.out.println(semaphore4.availablePermits());
    }
}
