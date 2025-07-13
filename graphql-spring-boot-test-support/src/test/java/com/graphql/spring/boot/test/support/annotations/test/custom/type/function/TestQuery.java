package com.graphql.spring.boot.test.support.annotations.test.custom.type.function;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLQueryResolver;

@GraphQLQueryResolver
public class TestQuery {

  @GraphQLField
  public static Foo foo() {
    return new Foo();
  }
}
