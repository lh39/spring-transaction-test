package de.lh39.springtransactiontest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lh39
 */
@SpringBootApplication
public class SpringTransactionTestApplication {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SpringTransactionTestApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SpringTransactionTestApplication.class, args);
  }
}