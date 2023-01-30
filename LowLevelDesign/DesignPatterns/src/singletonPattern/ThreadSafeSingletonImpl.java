package singletonPattern;

public class ThreadSafeSingletonImpl {
    private ThreadSafeSingletonImpl() {}

    //Ensures singleton object availability in main memory(shared memory space between threads) and
    //not thread local stack memory.
    private static volatile ThreadSafeSingletonImpl singleton = null;

    //You can also make this entire method to be synchronized but that will make it super slow.
    //All threads have to wait to retrieve object even though it was earlier created - not a preferred way.
    public static ThreadSafeSingletonImpl getInstance() {
        //first check is to ensure not every thread has to wait for synchronized block to enter
        //return the instance if instance already created.
        if (singleton == null) {
            //For first time instance to be created, multiple threads should not be allowed at once to enter the block.
            //synchronized keyword makes thread safe.
            synchronized (ThreadSafeSingletonImpl.class) {
                //As multiple threads can reach at synchronized block, they will be given access to enter the block once
                //first thread processing is done.
                //Since instance is already created by first thread, we don't want to create a new instance, hence second
                //null check on singleton object.
                //This is referred as Double-Checked Locking.
                //Head over to Concurrency folder to read about more multi-threading concepts.
                if(singleton == null) {
                    singleton = new ThreadSafeSingletonImpl();
                }
            }
        }
        return singleton;
    }
}
