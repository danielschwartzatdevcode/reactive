package com.devcode.completablefutures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

/**
 * Created by christopheryamba on 20/03/16.
 */
public class MyCompletable {

    public static CompletableFuture<Void> bestOf(Long n, Predicate<Long> first, Predicate<Long> second) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletableFuture<Boolean> firstFuture = CompletableFuture.supplyAsync(() -> first.test(n), executor);

        CompletableFuture<Boolean> secondFuture = CompletableFuture.supplyAsync(() -> second.test(n), executor);

        CompletableFuture<Void> winnerFuture = firstFuture.acceptEitherAsync(secondFuture, (val) -> System.out.println(String.format("%s %s", n, val ? "is prime" : "is not prime")), executor);

        return winnerFuture;
    }
}
