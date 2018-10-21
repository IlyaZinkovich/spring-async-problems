package io.github.ilyazinkovich.spring.async.good;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class AsyncObjectTest {

  private final TestExecutor testExecutor = new TestExecutor();
  private final AsyncObject asyncObject = new AsyncObject(testExecutor);

  @AfterEach
  void resetExecutor() {
    testExecutor.reset();
  }

  @Test
  void testAsyncMethod() {
    asyncObject.asyncMethod();
    assertTrue(testExecutor.isCompleted());
  }

  @Test
  void testExplicitlyAsyncMethod() {
    asyncObject.explicitlyAsyncMethod();
    assertTrue(testExecutor.isCompleted());
  }

  @Test
  void testRegularMethodCallingAsync() {
    asyncObject.regularMethodCallingAsync();
    assertTrue(testExecutor.isCompleted());
  }

  @Test
  void testRegularMethodCallingExplicitlyAsync() {
    asyncObject.regularMethodCallingExplicitlyAsync();
    assertTrue(testExecutor.isCompleted());
  }

  @Test
  void testAsyncMethodWithReturnValue() throws ExecutionException, InterruptedException {
    final CompletableFuture<Integer> result = asyncObject.asyncMethodWithReturnValue();
    assertTrue(testExecutor.isCompleted());
    assertEquals(Integer.valueOf(1), result.get());
  }

  @Test
  void testAsyncMethodThrowingException() throws ExecutionException, InterruptedException {
    final CompletableFuture<Integer> result =
        assertDoesNotThrow(asyncObject::asyncMethodThrowingException);
    assertEquals(Integer.valueOf(1), result.get());
  }
}
