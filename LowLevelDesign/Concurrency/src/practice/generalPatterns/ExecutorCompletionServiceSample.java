package practice.generalPatterns;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ExecutorCompletionServiceSample {
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

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        CompletionService<String> executorCompletionService = new ExecutorCompletionService<>(service);
        ArrayList<Future<String>> futureList = new ArrayList<>();
        futureList.add(executorCompletionService.submit(new CallableTest("Task#100")));
        futureList.add(executorCompletionService.submit(new CallableTest("Task#101")));

        for(int i=0;i<futureList.size();i++) {
            String res = executorCompletionService.take().get();
            System.out.println(res);
        }
        service.shutdown();
    }
}
