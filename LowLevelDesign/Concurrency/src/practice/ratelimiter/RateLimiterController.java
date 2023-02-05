package practice.ratelimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TokenBucket - System is processing fix number of requests per time. Example- users are configured with fixed capacity that
 * they can call system to. user1 can call system at 5 requests/min. So If within a single minute, there are requests more than 5,
 * the requests will not be served and discarded.
 * As the refresh rate is configured 1 minute, so the token bucket capacity will be refreshed to 5 every minute.
 *
 * LeakyBucket - FIFO structure. Bucket of fixed size. Example with size=3. The bucket can hold maximum of 3 requests at
 * a time and as the requests are processed, they are leaking from the bucket at a constant rate and more requests can be
 * added to the bucket.
 * If the bucket is full, the bucket will overflow and new requests will not be added to the bucket.
 *
 * Sliding Window - Calculate the rate in realtime instead of each window.
 *
 *
 * initial Implementation Reference - Youtube channel TechGranth
 * */
public class RateLimiterController {
    public static void main(String[] args) {
        UserBucketCreator userBucketCreator = new UserBucketCreator(123);

        ExecutorService service = Executors.newFixedThreadPool(10);
        for(int i=0;i<12;i++) {
            service.execute(() -> userBucketCreator.accessApplication(123));
        }

        service.shutdown();

//        UserTokenBucketCreator userTokenBucketCreator = new UserTokenBucketCreator(23);
//        ExecutorService service2 = Executors.newFixedThreadPool(10);
//        for(int i=0;i<12;i++) {
//            service2.execute(() -> userTokenBucketCreator.accessApplication(23));
//        }
//
//        service2.shutdown();
    }
}
