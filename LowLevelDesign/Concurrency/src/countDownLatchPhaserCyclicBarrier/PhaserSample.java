package countDownLatchPhaserCyclicBarrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserSample {
    public static class MyTask implements Runnable {
        private final Phaser phaser;
        MyTask(Phaser phaser) {
            this.phaser = phaser;
            phaser.register(); // the threads register themselves.
        }

        @Override
        public void run() {
            //thread reached the barrier point.
            phaser.arriveAndAwaitAdvance();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //current thread should not be accounted for in current phase.
            phaser.arriveAndDeregister();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Phaser phaser = new Phaser(1); // self register

        service.submit(new MyTask(phaser));
        service.submit(new MyTask(phaser));

        phaser.arriveAndAwaitAdvance();
        service.shutdown();
    }
}
