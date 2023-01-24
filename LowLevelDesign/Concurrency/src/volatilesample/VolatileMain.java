package volatilesample;

public class VolatileMain {

    /**
     * Example from
     * <a href="https://www.javatpoint.com/volatile-keyword-in-java">...</a>
     * */
    public static void main(String[] args) throws InterruptedException {
        int numOfThreads = 2;
        VolatileKeywordImpl volatileKeyword = new VolatileKeywordImpl();
        Thread[] threads = new Thread[numOfThreads];
        for(int i=0;i<numOfThreads;i++) {
            threads[i] = new VolatileThread(volatileKeyword);
        }

        for(int i=0;i<numOfThreads;i++) {
            threads[i].start();
        }

        for(int i=0;i<numOfThreads;i++) {
            threads[i].join();
        }
    }
}
