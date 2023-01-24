package volatilesample;

public class VolatileThread extends Thread {
    private final VolatileKeywordImpl volatileKeyword;
    public VolatileThread(VolatileKeywordImpl volatileKeyword) {
        this.volatileKeyword = volatileKeyword;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " Old value = " + volatileKeyword.getCount());
        volatileKeyword.incrementCountByOne();
        System.out.println("Thread " + Thread.currentThread().getId() + " new value = " + volatileKeyword.getCount());
    }
}
