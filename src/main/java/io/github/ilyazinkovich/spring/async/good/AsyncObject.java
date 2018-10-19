package io.github.ilyazinkovich.spring.async.good;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class AsyncObject {

  private final Executor executor;

  public AsyncObject(final Executor executor) {
    this.executor = executor;
  }

  public void asyncMethod() {
    CompletableFuture.runAsync(() -> System.out.println("execute asynchronously"), executor);
  }

  public CompletableFuture<Void> explicitlyAsyncMethod() {
    return CompletableFuture.runAsync(() -> System.out.println("execute asynchronously"), executor);
  }

  public void regularMethodCallingAsync() {
    asyncMethod();
  }

  public void regularMethodCallingExplicitlyAsync() {
    explicitlyAsyncMethod().thenRun(() -> System.out.println("callback"));
  }

  public CompletableFuture<Integer> asyncMethodWithRawReturnType() {
    return CompletableFuture.runAsync(() -> System.out.println("execute asynchronously"), executor)
        .thenApplyAsync(noResult -> 1);
  }

  public CompletableFuture<Integer> asyncMethodThrowingException() {
    return CompletableFuture.runAsync(() -> System.out.println("execute asynchronously"))
        .thenApply(noResult -> {
          if (true) {
            throw new RuntimeException("async exception");
          } else {
            return 1;
          }
        }).exceptionally(throwable -> {
          throwable.printStackTrace();
          return 1;
        });
  }
}
