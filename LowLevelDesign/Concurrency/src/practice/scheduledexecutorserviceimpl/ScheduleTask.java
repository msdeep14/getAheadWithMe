package practice.scheduledexecutorserviceimpl;

import java.util.concurrent.TimeUnit;

public class ScheduleTask {
    private Runnable runnable;
    private Long scheduledTime;
    private TaskType taskType;
    private Long period;
    private Long delay;
    private TimeUnit timeUnit;

    public ScheduleTask(Runnable runnable, Long scheduledTime, TaskType taskType, Long period, Long delay, TimeUnit timeUnit) {
        this.runnable = runnable;
        this.scheduledTime = scheduledTime;
        this.taskType = taskType;
        this.period = period;
        this.delay = delay;
        this.timeUnit = timeUnit;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public long getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(long scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
