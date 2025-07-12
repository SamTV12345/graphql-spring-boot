package com.graphql.spring.boot.test;

import org.junit.jupiter.api.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DisableSecurityConfiguration {

  @Bean
  @Order(0)
  public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception {
    // Disable security for tests
    http.authorizeRequests().anyRequest().permitAll();
    return http.build();
  }
}
