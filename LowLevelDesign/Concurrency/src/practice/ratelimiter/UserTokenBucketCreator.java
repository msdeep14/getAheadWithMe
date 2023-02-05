package practice.ratelimiter;

import practice.ratelimiter.strategy.TokenBucket;

import java.util.HashMap;
import java.util.Map;

public class UserTokenBucketCreator {
    Map<Integer, TokenBucket> bucketMap;
    public UserTokenBucketCreator(int id) {
        this.bucketMap = new HashMap<>();
        //Capacity allowed to user
        bucketMap.put(id, new TokenBucket(5, 5));
    }

    public void accessApplication(int id) {
        if(bucketMap.get(id).grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " -> Able to access the application via TokenBucket");
        } else {
            System.out.println("Too many requests!!!");
        }
    }
}
