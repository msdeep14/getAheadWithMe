package practice.threadsafestack;

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

        //some issues with the code thread-safety
        //TODO: fix it
        new Thread(new StackAddRunnableTest(1, st)).start();
        new Thread(new StackAddRunnableTest(2, st)).start();
        new Thread(new StackAddRunnableTest(3, st)).start();
        new Thread(new StackPopRunnableTest(st)).start();
        new Thread(new StackPopRunnableTest(st)).start();
        new Thread(new StackPopRunnableTest(st)).start();
        new Thread(new StackPopRunnableTest(st)).start();

    }
}
