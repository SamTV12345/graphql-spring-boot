package com.graphql.spring.boot.test.support.editor.altair;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import graphql.kickstart.autoconfigure.editor.altair.AltairAutoConfiguration;
import graphql.kickstart.autoconfigure.editor.altair.AltairController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/** @author Andrew Potter */
class AltairControllerTest extends AbstractAutoConfigurationTest {

  public AltairControllerTest() {
    super(AnnotationConfigWebApplicationContext.class, AltairAutoConfiguration.class);
  }

  @Test
  void altairLoads() {
    load(EnabledConfiguration.class);

    assertThat(this.getContext().getBean(AltairController.class)).isNotNull();
  }

  @Test
  void altairDoesNotLoad() {
    load(DisabledConfiguration.class);

    AbstractApplicationContext context = getContext();
    assertThatExceptionOfType(NoSuchBeanDefinitionException.class)
        .isThrownBy(() -> context.getBean(AltairController.class));
  }

  @Configuration
  @PropertySource("classpath:enabled-config.properties")
  static class EnabledConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
    }
  }

  @Configuration
  @PropertySource("classpath:disabled-config.properties")
  static class DisabledConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
    }
  }
}
