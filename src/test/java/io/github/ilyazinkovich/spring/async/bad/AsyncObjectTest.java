package io.github.ilyazinkovich.spring.async.bad;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AsyncConfiguration.class)
public class AsyncObjectTest {

  @Autowired
  private AsyncObject asyncObject;

  @Autowired
  private TestExecutor testExecutor;

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
  void testRegularMethodCallingAsync() {
    asyncObject.regularMethodCallingAsync();
    assertFalse(testExecutor.isCompleted());
  }

  @Test
  void testAsyncMethodWithNonExistingExecutor() {
    assertThrows(NoSuchBeanDefinitionException.class,
        () -> asyncObject.asyncMethodWithNonExistingExecutor());
  }

  @Test
  void testAsyncMethodWithRawReturnType() {
    final Integer result = asyncObject.asyncMethodWithRawReturnType();
    assertNull(result);
    assertTrue(testExecutor.isCompleted());
  }

  @Test
  void testAsyncMethodThrowingException() {
    assertDoesNotThrow(() -> asyncObject.asyncMethodThrowingException());
  }
}
