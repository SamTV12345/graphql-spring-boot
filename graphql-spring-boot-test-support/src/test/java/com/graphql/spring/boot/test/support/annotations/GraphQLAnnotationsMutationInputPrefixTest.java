package com.graphql.spring.boot.test.support.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import graphql.schema.GraphQLSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@DisplayName("Testing input prefix")
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {"graphql.annotations.input-prefix=Prefix", "graphql.annotations.input-suffix="})
@ActiveProfiles({"annotations", "test", "mutation-test"})
class GraphQLAnnotationsMutationInputPrefixTest {

  @Autowired private GraphQLSchema graphQLSchema;

  @Test
  @DisplayName("Assert that input prefix is used.")
  void testInputPrefix() {
    // THEN
    assertThat(graphQLSchema.getType("PrefixTestModel")).isNotNull();
  }
}
