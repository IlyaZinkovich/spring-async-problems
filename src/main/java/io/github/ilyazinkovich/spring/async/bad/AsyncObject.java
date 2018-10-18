package io.github.ilyazinkovich.spring.async.bad;

import java.util.concurrent.Future;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public class AsyncObject {

  @Async("asyncExecutor")
  public void asyncMethod() {
    System.out.println("execute asynchronously");
  }

  public void regularMethodCallingAsync() {
    asyncMethod();
  }

  @Async("executor")
  public void asyncMethodWithNonExistingExecutor() {
    System.out.println("execute asynchronously");
  }

  @Async("asyncExecutor")
  public Integer asyncMethodWithRawReturnType() {
    System.out.println("execute asynchronously");
    return 1;
  }

  @Async("asyncExecutor")
  public Future<Integer> asyncMethodWithFutureReturnType() {
    System.out.println("execute asynchronously");
    return new AsyncResult<>(1);
  }

  @Async("asyncExecutor")
  public void asyncMethodThrowingException() {
    System.out.println("execute asynchronously");
    throw new RuntimeException("async exception");
  }
}
