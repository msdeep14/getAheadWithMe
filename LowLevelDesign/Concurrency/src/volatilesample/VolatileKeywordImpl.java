package volatilesample;


public class VolatileKeywordImpl {
    private static volatile int count = 1;
    public int getCount() {
        return count;
    }
    public void incrementCountByOne() {
        /**
         * Intellij editor highlights below waring on the below piece of code.
         * Non-atomic operation on volatile field 'count'
         *
         * Though there is no issue found while running the main method. Example run with numOfThreads=2 and count=1
         * Thread 13 Old value = 1
         * Thread 12 Old value = 1
         * Thread 13 new value = 2
         * Thread 12 new value = 3
         *
         * With volatile keyword, what could be the problem?
         * It is set of two operations - Read and then increment -> a compound operation and not an atomic operation.
         * There is a possibility that Thread-13 reads the value before Thread-12 increments the value.
         *
         *
         * Thread-12                        Thread-13
         * Read value(=1)
         *                                  Read value(=1)
         * Add 1 and write(=2)
         *                                  Add 1 and write(=2)
         *
         * To solve the issue,
         * 1. synchronized keyword can be used which ensures only single thread access the method at once.
         * The method definition will be replaced by below, and it will also resolve the intellij warning -->
         *
         * public synchronized void incrementCountByOne() {
         *     count = count + 1;
         * }
         *
         * 2. Another way is to use AtomicInteger, this will ensure both read and write operations are performed atomically.
         * AtomicInteger count = new AtomicInteger(1);
         * public int getCount() {
         *     return count.get();
         * }
         *
         * public void incrementCountByOne() {
         *     count.getAndIncrement();
         * }
         *
         *
         * */
        count = count + 1;
    }
}
