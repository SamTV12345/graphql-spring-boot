package com.graphql.spring.boot.test.support.annotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

  public static void main(final String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }
}
