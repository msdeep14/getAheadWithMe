package practice.generalPatterns;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolSample {

    public static class Fibonacci extends RecursiveTask<Integer> {
        private final Integer n;
        public Fibonacci(Integer n) {
            this.n = n;
        }
        @Override
        protected Integer compute() {
            if(n == 0) return 0;
            if(n == 1) return 1;

            Fibonacci f1 = new Fibonacci(n-1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n-1);
            f2.fork();
            return f1.join() + f2.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        int n = 6;
        Integer ans = forkJoinPool.invoke(new Fibonacci(n));
        System.out.println(n + "th number in Fibonacci series: " + ans);

        forkJoinPool.awaitTermination(5, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }
}
