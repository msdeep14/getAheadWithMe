public class ThreadLocalImpl {
    public static ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 1);

}
