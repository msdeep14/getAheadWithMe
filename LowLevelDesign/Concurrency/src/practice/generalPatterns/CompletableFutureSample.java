package practice.generalPatterns;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Give it a watch --> <a href="https://www.youtube.com/watch?v=9ueIL0SwEWI&ab_channel=OracleDevelopers">...</a>
 * */
public class CompletableFutureSample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Hello Future");

        System.out.println(completableFuture.completeAsync(() -> "Hello Async").completeAsync(() -> "Async2").get());
        String value = completableFuture.get();
        System.out.println(value);
    }
}
