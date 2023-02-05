package practice.ratelimiter.strategy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LeakyBucket extends RateLimiter {
    BlockingQueue<Integer> queue;

    public LeakyBucket(int capacity) {
        super(capacity);
        /**
         * {@link practice.producerconsumer.lockCondition.MyBlockingQueue} for implementing your
         * own blocking queue.
         * */
        this.queue = new LinkedBlockingDeque<>(capacity);
    }

    @Override
    public boolean grantAccess() {
        if(queue.remainingCapacity() > 0) {
            //There will be consumer application that will consume messages from this queue
            //and as that happens, messages from queue will be removed.
            queue.add(1);
            return true;
        }
        return false;
    }
}
