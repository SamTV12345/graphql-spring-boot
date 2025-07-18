package com.graphql.spring.boot.test.support;

import static org.assertj.core.api.Assertions.assertThat;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import java.io.IOException;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@DisplayName("Testing that custom scalar beans are properly registered.")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = com.graphql.spring.boot.test.support.annotations.TestApplication.class)
@ActiveProfiles({"annotations", "test", "custom-scalar-test"})
class GraphQLAnnotationsCustomScalarTest {

  @Autowired private GraphQLTestTemplate graphQLTestTemplate;

  @Test
  @DisplayName("Assert that custom scalars work properly.")
  void testQueryWithCustomScalar() throws IOException {
    // WHEN
    final GraphQLResponse graphQLResponse =
        graphQLTestTemplate.postForResource("annotations/queries/test-custom-scalar-query.graphql");
    // THEN
    assertThat(graphQLResponse.get("$.data.randomUUID", UUID.class)).isNotNull();
  }
}
