package com.graphql.spring.boot.test.support.annotations.test.subscription;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLQueryResolver;

@GraphQLQueryResolver
public class DummyQuery {

  @GraphQLField
  public static String dummyQuery() {
    return "A GraphQL query is required.";
  }
}
