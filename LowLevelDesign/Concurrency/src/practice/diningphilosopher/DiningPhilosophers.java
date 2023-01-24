package practice.diningphilosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosophers {

    public static void main(String[] args) {
        final int numOfPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numOfPhilosophers];
        Object[] forks = new Object[numOfPhilosophers];
        ExecutorService executorService = Executors.newFixedThreadPool(numOfPhilosophers);

        for(int i=0;i< forks.length;i++) {
            forks[i] = new Object();
        }

        for(int i=0;i< philosophers.length;i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i+1)%forks.length];
            if(i%2 == 0) {
                executorService.execute(new Philosopher(leftFork, rightFork, i+1));
            } else {
                executorService.execute(new Philosopher(rightFork, leftFork, i+1));
            }
        }

        executorService.shutdown();
    }
}
