package practice.threadsafecache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LRUCacheController {

    public static void main(String[] args) {
        //create lruCache with capacity=4
        ThreadSafeLRUCache<Integer, Integer> lruCache = new ThreadSafeLRUCache<>(4);

        /*ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> new Runnable() {
            @Override
            public void run() {
                System.out.println("Put (1,1) to cache");
                lruCache.put(1,1);
            }
        });

        service.execute(() -> new Runnable() {
            @Override
            public void run() {
                System.out.println(lruCache.get(1));
            }
        });

        service.execute(() -> new Runnable() {
            @Override
            public void run() {
                System.out.println(lruCache.get(2));
            }
        });

        service.shutdown();*/


        new Thread(() -> new Runnable() {
            @Override
            public void run() {
                System.out.println("Put (1,1) to cache");
                lruCache.put(1,1);
            }
        }).start();

        new Thread(() -> new Runnable() {
            @Override
            public void run() {
                System.out.println(lruCache.get(1));
            }
        }).start();

        new Thread(() -> new Runnable() {
            @Override
            public void run() {
                System.out.println(lruCache.get(2));
            }
        }).start();
    }
}
