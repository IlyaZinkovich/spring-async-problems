package io.github.ilyazinkovich.spring.async.bad;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class AsyncConfiguration {

  @Bean
  AsyncObject asyncObject() {
    return new AsyncObject();
  }

  @Bean
  Executor asyncExecutor() {
    return new TestExecutor();
  }
}
