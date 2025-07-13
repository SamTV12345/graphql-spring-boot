package com.graphql.spring.boot.test.support.editor.altair;

import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;

/** @author Moncef AOUDIA */
public abstract class AbstractAutoConfigurationTest {

  private final Class<? extends AbstractApplicationContext> contextClass;
  private final Class<?> autoConfiguration;

  private AbstractApplicationContext context;

  protected AbstractAutoConfigurationTest(Class<?> autoConfiguration) {
    this(AnnotationConfigApplicationContext.class, autoConfiguration);
  }

  protected AbstractAutoConfigurationTest(
      Class<? extends AbstractApplicationContext> contextClass, Class<?> autoConfiguration) {
    assert AnnotationConfigRegistry.class.isAssignableFrom(contextClass);
    this.contextClass = contextClass;
    this.autoConfiguration = autoConfiguration;
  }


  @Bean
  public GraphQLErrorHandler graphQLErrorHandler () {
    return list -> List.of();
  }

  @AfterEach
  public void tearDown() {
    if (this.context != null) {
      this.context.close();
      this.context = null;
    }
  }

  protected void load(Class<?> config, String... environment) {
    try {
      this.context = contextClass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Failed to instantiate testing context", e);
    }

    if (environment != null && environment.length > 0) {
      TestPropertyValues.of(environment).applyTo(context);
    }

    getRegistry().register(config);
    getRegistry().register(autoConfiguration);
    getContext().refresh();
  }

  public AnnotationConfigRegistry getRegistry() {
    return (AnnotationConfigRegistry) context;
  }

  public AbstractApplicationContext getContext() {
    return context;
  }
}
