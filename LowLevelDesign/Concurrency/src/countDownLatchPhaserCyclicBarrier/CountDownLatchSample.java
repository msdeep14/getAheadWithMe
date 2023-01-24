package countDownLatchPhaserCyclicBarrier;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchSample {
    private CountDownLatch countDownLatch;

    CountDownLatchSample(CountDownLatch latch) {
        this.countDownLatch = latch;
    }

    private void sampleMethod() {
        System.out.println("CountDownLatch reducing by 1");
        countDownLatch.countDown();
    }

    public void performSystemExecution() {
        System.out.println("Performing system execution");
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        CountDownLatchSample countDownLatchSample = new CountDownLatchSample(latch);
        Thread thread1 = new Thread(countDownLatchSample::sampleMethod);
        Thread thread2 = new Thread(countDownLatchSample::sampleMethod);
        thread1.start();
        thread2.start();

        //wait for thread-1 and thread-2 to complete.
        latch.await();

        //setup done
        Thread thread3 = new Thread(countDownLatchSample::performSystemExecution);
        thread3.start();
    }
}
