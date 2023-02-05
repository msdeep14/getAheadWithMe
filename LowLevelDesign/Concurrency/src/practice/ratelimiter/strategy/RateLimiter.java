package practice.ratelimiter.strategy;

public abstract class RateLimiter {
    protected final int limit;
    protected RateLimiter(int limit) {
        this.limit = limit;
    }
    public abstract boolean grantAccess();
}
