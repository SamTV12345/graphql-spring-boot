package com.graphql.spring.boot.test.support.annotations.test.prettify;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLQueryResolver;

@GraphQLQueryResolver
public class PrettifyQuery {

  @GraphQLField
  public static String getSomeValue() {
    return "some value";
  }
}
