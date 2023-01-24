package practice.threadsafesingleton;

/**
 *
 * */
public class ThreadSafeSingleton {
    // volatile to ensure instance is accessed from main memory and not thread local cache.
    private static volatile ThreadSafeSingleton instance = null;

    private ThreadSafeSingleton() {}

    public static ThreadSafeSingleton getInstance() {
        // first if to improve performance by not putting every thread to wait condition while accessing the instance.
        if(instance == null) {
            // synchronized to ensure multiple threads don't try to initialize the class at the same time.
            synchronized (ThreadSafeSingleton.class) {
                if(instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
