package practice.threadsafecache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LRUCacheController {

    public static class RunnableTest implements Runnable {
        private ThreadSafeLRUCache<Integer, Integer> lruCache;
        public RunnableTest(ThreadSafeLRUCache<Integer, Integer> lruCache) {
            this.lruCache = lruCache;
        }
        @Override
        public void run() {
            lruCache.put(1,2);
        }
    }

    public static class RunnableGetTest implements Runnable {
        private ThreadSafeLRUCache<Integer, Integer> lruCache;
        private Integer key;
        public RunnableGetTest(ThreadSafeLRUCache<Integer, Integer> lruCache, int key) {
            this.lruCache = lruCache;
            this.key = key;
        }
        @Override
        public void run() {
            System.out.println("Value for key: " + key + " : " + lruCache.get(key));
        }
    }

    public static void main(String[] args) {
        //create lruCache with capacity=4
        ThreadSafeLRUCache<Integer, Integer> lruCache = new ThreadSafeLRUCache<>(4);

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new RunnableTest(lruCache));
        service.submit(new RunnableGetTest(lruCache, 1));
        service.submit(new RunnableGetTest(lruCache, 6));

        service.shutdown();



    }
}
