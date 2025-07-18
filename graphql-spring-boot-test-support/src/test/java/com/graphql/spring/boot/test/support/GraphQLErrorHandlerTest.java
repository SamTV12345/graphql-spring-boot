package com.graphql.spring.boot.test.support;

import com.graphql.spring.boot.test.TestUtils;
import com.graphql.spring.boot.test.support.editor.altair.AbstractAutoConfigurationTest;
import graphql.ErrorClassification;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.kickstart.autoconfigure.web.servlet.GraphQLWebAutoConfiguration;
import graphql.kickstart.execution.GraphQLObjectMapper;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.SchemaParser;
import graphql.language.SourceLocation;
import graphql.schema.GraphQLSchema;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Disabled
class GraphQLErrorHandlerTest extends AbstractAutoConfigurationTest {

  private GraphQL gql;
  private GraphQLObjectMapper objectMapper;

  public GraphQLErrorHandlerTest() {
    super(AnnotationConfigWebApplicationContext.class, GraphQLWebAutoConfiguration.class);
  }

  @BeforeEach
  public void setUp() {
    System.setProperty("graphql.tools.schemaLocationPattern", "graphql/error-handler-test.graphql");
    System.setProperty("graphql.servlet.exception-handlers-enabled", "true");
    load(BaseConfiguration.class);

    GraphQLSchema schema = getContext().getBean(GraphQLSchema.class);
    gql = GraphQL.newGraphQL(schema).build();

    objectMapper = GraphQLObjectMapper.newBuilder().withGraphQLErrorHandler(graphQLErrorHandler()).build();
  }

  @Test
  void illegalArgumentExceptionShouldBeHandledConcretely() {
    TestUtils.assertGraphQLError(
        gql,
        "query { illegalArgumentException }",
        new ThrowableGraphQLError(
            new IllegalArgumentException("Some argument"), "Custom illegal argument"),
        objectMapper);
  }

  @Test
  void illegalStateExceptionShouldBeHandledByCatchAll() {
    TestUtils.assertGraphQLError(
        gql,
        "query { illegalStateException }",
        new ThrowableGraphQLError(new IllegalStateException("Illegal state"), "Catch all handler"),
        objectMapper);
  }

  @Configuration
  static class BaseConfiguration {

    public static class Query implements GraphQLQueryResolver {

      boolean illegalArgumentException() {
        throw new IllegalArgumentException("Illegal argument");
      }

      boolean illegalStateException() {
        throw new IllegalStateException("Illegal state");
      }

      @ExceptionHandler(IllegalArgumentException.class)
      public ThrowableGraphQLError handle(IllegalArgumentException e) {
        return new ThrowableGraphQLError(e, "Custom illegal argument");
      }

      @ExceptionHandler(Throwable.class)
      public GraphQLError handle(Throwable e) {
        return new ThrowableGraphQLError(e, "Catch all handler");
      }

      @Bean
      Query queryResolver() {
        return new Query();
      }

      @Bean
      GraphQLSchema schema() {
        SchemaParser schemaParser =
            SchemaParser.newParser()
                .file("graphql/error-handler-test.graphql")
                .resolvers(queryResolver())
                .build();
        return schemaParser.makeExecutableSchema();
      }
    }
  }
}
