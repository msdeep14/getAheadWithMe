package volatilesample;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceImpl {
    public static void main(String[] args) {
        final String[] msg = {"hello"};
        AtomicReference<String> atomicReference = new AtomicReference<>(msg[0]);

        new Thread("Sample thread") {
            public void run() {
                boolean isSuccess = atomicReference.compareAndSet(msg[0], "new Hello");
                System.out.println("CompareAndSet is success? - " + isSuccess);
            }
        }.start();
        System.out.println("Updated msg is: " + msg[0]);
    }
}
