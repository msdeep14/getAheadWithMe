package practice.ratelimiter.strategy;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket extends RateLimiter {
    //Refresh Rate - available Tokens per second
    private final int refreshRate;
    //current capacity of token bucket
    private final AtomicInteger currentCapacity;

    //last Time the bucket was updated.
    private final AtomicLong lastUpdatedTime;

    public TokenBucket(int capacity, int refreshRate) {
        super(capacity);
        this.refreshRate = refreshRate;
        currentCapacity = new AtomicInteger(capacity);
        lastUpdatedTime = new AtomicLong(System.currentTimeMillis());
    }

    private void refillToken() {
        long currentTime = System.currentTimeMillis();
        long additionalToken = (currentTime - lastUpdatedTime.get()) / 1000 * refreshRate;
        long currCapacity = Math.min(currentCapacity.get() + additionalToken, limit);
        currentCapacity.getAndSet((int) currCapacity);
        lastUpdatedTime.getAndSet(currentTime);

    }
    @Override
    public boolean grantAccess() {
        refillToken();
        if(currentCapacity.get() > 0) {
            currentCapacity.decrementAndGet();
            return true;
        }
        return false;
    }
}
