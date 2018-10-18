package io.github.ilyazinkovich.spring.async.bad;

import java.util.concurrent.Executor;

public class TestExecutor implements Executor {

  private boolean completed = false;

  @Override
  public void execute(final Runnable command) {
    command.run();
    completed = true;
  }

  public void reset() {
    completed = false;
  }

  public boolean isCompleted() {
    return completed;
  }
}
