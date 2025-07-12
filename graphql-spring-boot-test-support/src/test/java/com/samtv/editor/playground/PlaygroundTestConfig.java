package com.samtv.editor.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.autoconfigure.editor.playground.PlaygroundAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;

@EnableAutoConfiguration(exclude = WebFluxAutoConfiguration.class)
// @EnableWebSecurity
@ContextConfiguration(classes = {PlaygroundAutoConfiguration.class, ObjectMapper.class})
class PlaygroundTestConfig { // extends WebSecurityConfigurerAdapter {

  //  @Override
  //  protected void configure(final HttpSecurity http) throws Exception {
  //    http.authorizeRequests().anyRequest().permitAll();
  //  }
}
