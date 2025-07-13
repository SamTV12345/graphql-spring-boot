package com.graphql.spring.boot.test.support.annotations.test.query;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import java.util.Optional;

@GraphQLQueryResolver
public class TestQuery {

  @GraphQLField
  @GraphQLNonNull
  public static String hello(final @GraphQLName("who") String who) {
    return String.format("Hello, %s!", Optional.ofNullable(who).orElse("World"));
  }
}
