package locksample;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionSample {
    private final Lock lock = new ReentrantLock();
    private final Condition myCondition = lock.newCondition();

    private void method1() {
        lock.lock();
        try {
            // perform initial set of operations
            System.out.println("Executing method-1 Stage-1");
            System.out.println("Awaiting for condition to satisfy");
            myCondition.await();
            System.out.println("Executing method-2 Stage-2");
            // perform additional operations after condition satisfied
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private void method2() {
        lock.lock();
        try {
            //perform required operations.
            System.out.println("Executing method-2");
            System.out.println("signal that condition is satisfied");
            myCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * This code block assumes that thread-1 is executed before thread-2
         *
         * There is no surity that this will happen, thread-2 can be executed before thread-1 as well
         * because both are invoked in parallel.
         *
         * One such implementation could be by usage of CountDownLatch, refer {@link countDownLatchPhaserCyclicBarrier.CountDownLatchSample}
         * */
        ConditionSample conditionSample = new ConditionSample();
        Thread thread1 = new Thread(conditionSample::method1);
        thread1.start();


        Thread thread2 = new Thread(conditionSample::method2);
        thread2.start();
    }
}
