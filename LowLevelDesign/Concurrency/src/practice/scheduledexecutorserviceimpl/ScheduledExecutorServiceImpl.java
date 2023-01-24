package practice.scheduledexecutorserviceimpl;

import java.util.concurrent.TimeUnit;

/**
 * <a href="https://leetcode.com/discuss/interview-question/341504/uber-implement-scheduledexecutorservice">...</a>
 * */
public class ScheduledExecutorServiceImpl {

    /**
     * Creates and executes a one-shot action that becomes enabled after the given delay.
     */
    public void schedule(Runnable command, long delay, TimeUnit unit) {

    }

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given period; that is executions will commence after initialDelay then
     * initialDelay+period, then initialDelay + 2 * period, and so on.
     */
    public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {

    }

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given delay between the termination of one execution and the commencement of the next.
     */
    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {

    }

}
