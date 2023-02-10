package practice.diningphilosopher;

import java.util.Random;

public class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;
    private final int philosopherNumber;

    public Philosopher(final Object leftFork, final Object rightFork, final int philosopherNumber) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.philosopherNumber = philosopherNumber;
    }

    private void performAction(final String action, final int philosopherNumber) throws InterruptedException {
        int sleepTime = new Random().nextInt(1000);

        Thread.sleep(sleepTime);
        System.out.println(action + "  action performed by Philosopher " + philosopherNumber + " for time: "+ sleepTime);
    }

    @Override
    public void run() {
        try {
            while(true) {
                //thinking
                performAction("Thinking", philosopherNumber);
                /**
                 * You can also use locks instead of synchronized keyword --> can be an ask in interviews
                 *
                 * Lock leftLock = new ReentrantLock();
                 * Lock rightLock = new ReentrantLock();
                 *
                 *
                 * */
                synchronized (leftFork) {
                    performAction("Pick up left fork", philosopherNumber);
                    synchronized (rightFork) {
                        performAction("pick up right fork", philosopherNumber);
                        performAction("eating", philosopherNumber);
                        performAction("put down both forks", philosopherNumber);
                    }
                }
                //thinking
                performAction("Thinking", philosopherNumber);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            System.out.println("Exception occurred on Philosopher run method: " + e);
            throw new RuntimeException("Exception encountered: " + e);
        }
    }
}
