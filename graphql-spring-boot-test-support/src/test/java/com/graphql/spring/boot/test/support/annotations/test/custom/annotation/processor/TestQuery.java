package com.graphql.spring.boot.test.support.annotations.test.custom.annotation.processor;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLQueryResolver;

@GraphQLQueryResolver
public class TestQuery {

  @GraphQLField
  public String someQuery() {
    return "some value";
  }
}
