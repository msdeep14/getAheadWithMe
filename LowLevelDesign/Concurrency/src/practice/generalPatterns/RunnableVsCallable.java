package practice.generalPatterns;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class RunnableVsCallable {

    public static class RunnableTest implements Runnable {
        private final int i;
        public RunnableTest(int i) {
            this.i = i;
        }
        @Override
        public void run() {
            System.out.println("Executing runnable task#" + i);
        }
    }

    public static class CallableTest implements Callable<String> {
        private final String value;
        public CallableTest(String value) {
            this.value = value;
        }

        @Override
        public String call() {
            return value;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        //execute() - only Runnable allowed, no output is returned
        service.execute(new RunnableTest(1));
        service.execute(new RunnableTest(2));

        //submit() - supports both runnable and callable, can return output wrapped in Future
        Future<String> future1 = service.submit(new CallableTest("Did you subscribed to @MsDeepSingh on YouTube"));
        Future<String> future2 = service.submit(new CallableTest("Do it right away!!!"));
        System.out.println(future1.get());
        System.out.println(future2.get());

        //invokeAll() - supports callable, can return output wrapped in Future
        final List<? extends Callable<String>> callableList = Arrays.asList(new CallableTest("Task#100"), new CallableTest("Task#101"));
        List<Future<String>> futures = service.invokeAll(callableList);
        for(Future<String> future: futures) {
            System.out.println(future.get());
        }

        service.shutdown();
    }
}
