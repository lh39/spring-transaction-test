[![Build Status](https://travis-ci.org/lh39/spring-transaction-test.svg?branch=master)](https://travis-ci.org/lh39/spring-transaction-test)

# Testing Spring Boot 1.5 data-jpa transaction behavior

This repository contains code to demonstrate Spring Boot transaction behavior. In particular it shows, how wrapping of multiple transactional sub-executions leads to a wrapped single transaction.

* see `@Transactional` behavior through [SpringTransactionTestApplicationTests.java](src/test/java/de/lh39/springtransactiontest/SpringTransactionTestApplicationTests.java)

## Run the application  
```
mvn spring-boot:run
```