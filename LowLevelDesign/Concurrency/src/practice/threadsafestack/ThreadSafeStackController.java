package practice.threadsafestack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeStackController {

    public static class StackAddRunnableTest implements Runnable {
        private final int i;
        ThreadSafeStack<Integer> st;
        public StackAddRunnableTest(int i, ThreadSafeStack<Integer> st) {
            this.i = i;
            this.st = st;
        }

        @Override
        public void run() {
            st.push(i);
        }
    }

    public static class StackPopRunnableTest implements Runnable {
        ThreadSafeStack<Integer> st;
        public StackPopRunnableTest(ThreadSafeStack<Integer> st) {
            this.st = st;
        }

        @Override
        public void run() {
            st.pop();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeStack<Integer> st = new ThreadSafeStack<>();

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new StackAddRunnableTest(1, st));
        service.submit(new StackAddRunnableTest(2, st));
        service.submit(new StackPopRunnableTest(st));
        service.submit(new StackPopRunnableTest(st));
        service.submit(new StackPopRunnableTest(st));
        service.submit(new StackPopRunnableTest(st));

        service.shutdown();

    }
}
