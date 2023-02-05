package practice.scheduledexecutorserviceimpl;

import java.util.concurrent.TimeUnit;

public class MainController {

    private static Runnable getRunnableTask(String s) {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(s + " started at " + System.currentTimeMillis()/1000);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(s + " ended at " + System.currentTimeMillis()/1000);
            }
        };
    }

    public static void main(String[] args) {
        ScheduledExecutorServiceImpl service = new ScheduledExecutorServiceImpl(5);
        Runnable task1 = getRunnableTask("task#1");
        service.schedule(task1, 1, TimeUnit.SECONDS);
        Runnable task2 = getRunnableTask("task#2");
        service.scheduleAtFixedRate(task2, 1, 2, TimeUnit.SECONDS);
        Runnable task3 = getRunnableTask("task#3");
        service.scheduleWithFixedDelay(task3, 1, 2, TimeUnit.SECONDS);
        service.start();
    }


}
