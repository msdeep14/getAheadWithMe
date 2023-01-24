import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  Concurrency - Dealing with multiple tasks happening at once. --> use concurrency tools
 * Locks
 *  *  synchronized keyword
 *  *  Atomic Classes
 *  *  Concurrent Data Structures example ConcurrentHashMap, BlockingQueue
 *  *  CompletableFuture
 *  *  CountdownLatch/Phaser/CyclicBarrier/Semaphore
 *
 *  Parallelism - Performing multiple tasks at once. --> use threads/threadpools
 *
 *
 * */
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final Integer value = ThreadLocalImpl.counter.get();
        System.out.println(value);
    }
}
