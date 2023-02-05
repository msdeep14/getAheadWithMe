package practice.ratelimiter;

import practice.ratelimiter.strategy.LeakyBucket;

import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {
    Map<Integer, LeakyBucket> bucketMap;
    public UserBucketCreator(int id) {
        this.bucketMap = new HashMap<>();
        //Capacity allowed to user
        bucketMap.put(id, new LeakyBucket(10));
    }

    public void accessApplication(int id) {
        if(bucketMap.get(id).grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " -> Able to access the application");
        } else {
            System.out.println("Too many requests!!!");
        }
    }
}
