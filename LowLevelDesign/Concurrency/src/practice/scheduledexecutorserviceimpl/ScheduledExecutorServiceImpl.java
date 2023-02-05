package practice.scheduledexecutorserviceimpl;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <a href="https://leetcode.com/discuss/interview-question/341504/uber-implement-scheduledexecutorservice">...</a>
 * code referenced from comments section
 * */
public class ScheduledExecutorServiceImpl {

    private final PriorityQueue<ScheduleTask> taskQueue;
    private final ReentrantLock lock;
    private final Condition newTaskAdded;
    private final ThreadPoolExecutor threadPoolExecutor;

    public ScheduledExecutorServiceImpl(int workerThreadSize) {
        this.taskQueue = new PriorityQueue<>(Comparator.comparingLong(ScheduleTask::getScheduledTime));
        this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(workerThreadSize);
        this.lock = new ReentrantLock();
        this.newTaskAdded = lock.newCondition();
    }

    public void start() {
        long timeToSleep = 0;
        while(true) {
            lock.lock();
            try {
                while(taskQueue.isEmpty()) {
                    newTaskAdded.await();
                }
                while(!taskQueue.isEmpty()) {
                    timeToSleep = taskQueue.peek().getScheduledTime() - System.currentTimeMillis();
                    if(timeToSleep < 0 ) {
                        break;
                    }
                    newTaskAdded.await(timeToSleep, TimeUnit.MILLISECONDS);
                }
                ScheduleTask task = taskQueue.poll();
                long newScheduledTime = 0;
                switch (task.getTaskType()) {
                    case SCHEDULE:
                        threadPoolExecutor.submit(task.getRunnable());
                        break;
                    case SCHEDULE_AT_FIXED_DELAY:
                        newScheduledTime = System.currentTimeMillis() + task.getTimeUnit().toMillis(task.getDelay());
                        threadPoolExecutor.submit(task.getRunnable());
                        task.setScheduledTime(newScheduledTime);
                        taskQueue.add(task);
                        break;
                    case SCHEDULE_AT_FIXED_PERIOD:
                        Future<?> future = threadPoolExecutor.submit(task.getRunnable());
                        future.get();
                        newScheduledTime = System.currentTimeMillis() + task.getTimeUnit().toMillis(task.getPeriod());
                        task.setScheduledTime(newScheduledTime);
                        taskQueue.add(task);
                        break;
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }


    /**
     * Creates and executes a one-shot action that becomes enabled after the given delay.
     */
    public void schedule(Runnable command, long delay, TimeUnit unit) {
        lock.lock();
        try {
            long scheduledTime = System.currentTimeMillis() + unit.toMillis(delay);
            ScheduleTask task = new ScheduleTask(command, scheduledTime, TaskType.SCHEDULE, null, null, unit);
            taskQueue.add(task);
            newTaskAdded.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given period; that is executions will commence after initialDelay then
     * initialDelay+period, then initialDelay + 2 * period, and so on.
     */
    public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        lock.lock();
        try {
            long scheduledTime = System.currentTimeMillis() + unit.toMillis(initialDelay);
            ScheduleTask task = new ScheduleTask(command, scheduledTime, TaskType.SCHEDULE_AT_FIXED_DELAY, period, null, unit);
            taskQueue.add(task);
            newTaskAdded.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
     * subsequently with the given delay between the termination of one execution and the commencement of the next.
     */
    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        lock.lock();
        try {
            long scheduledTime = System.currentTimeMillis() + unit.toMillis(initialDelay);
            ScheduleTask task = new ScheduleTask(command, scheduledTime, TaskType.SCHEDULE_AT_FIXED_PERIOD, null, delay, unit);
            taskQueue.add(task);
            newTaskAdded.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
