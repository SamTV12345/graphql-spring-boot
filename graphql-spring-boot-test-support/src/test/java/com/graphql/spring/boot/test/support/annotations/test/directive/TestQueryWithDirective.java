package com.graphql.spring.boot.test.support.annotations.test.directive;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLQueryResolver;

@GraphQLQueryResolver
public class TestQueryWithDirective {

  @GraphQLField
  @UpperCaseDirective
  public static String queryWithDirective() {
    return "this should be uppercase";
  }
}
