package practice.threadsafehashmap;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * leetcode problem without synchronization - <a href="https://leetcode.com/problems/design-hashmap/description/">...</a>
 * */
public class ThreadSafeHashMapController {

    public static class PutRunnableHashMap<K,V> implements Runnable {
        K key;
        V value;
        ThreadSafeHashMap<K,V> threadSafeHashMap;
        public PutRunnableHashMap(K key, V value, ThreadSafeHashMap<K,V> threadSafeHashMap) {
            this.key = key;
            this.value = value;
            this.threadSafeHashMap = threadSafeHashMap;
        }
        @Override
        public void run() {
            threadSafeHashMap.put(key, value);
        }
    }

    public static class GetCallableHashMap<K,V> implements Callable {
        K key;
        ThreadSafeHashMap<K,V> threadSafeHashMap;
        public GetCallableHashMap(K key, ThreadSafeHashMap<K,V> threadSafeHashMap) {
            this.key = key;
            this.threadSafeHashMap = threadSafeHashMap;
        }

        @Override
        public Object call() throws Exception {
            return threadSafeHashMap.get(key);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        ThreadSafeHashMap<Integer, Integer> hashMap = new ThreadSafeHashMap<>();
//        hashMap.put(1,5);
//        hashMap.put(4,10);
//        System.out.println(hashMap.get(4));
//        System.out.println(hashMap.get(10));

        //not possible to pass callable to thread constructor, using ExecutorService instead.
        /*new Thread(new PutRunnableHashMap<>(1,5,hashMap)).start();
        new Thread(new PutRunnableHashMap<>(4,10, hashMap)).start();*/

        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(new PutRunnableHashMap<>(1,5,hashMap));
        service.submit(new PutRunnableHashMap<>(4,10,hashMap));
        Future<Integer> res = service.submit(new GetCallableHashMap<>(1,hashMap));
        //System.out.println("Value for key(1):" + res.get());
        Integer val = res.get();

        FileWriter fileWriter = new FileWriter("/home/mandeep/Documents/getAheadWithMe/LowLevelDesign/Concurrency/src/practice/threadsafehashmap/sample.txt");
        fileWriter.write(val);
        fileWriter.close();

        service.shutdown();
    }
}
