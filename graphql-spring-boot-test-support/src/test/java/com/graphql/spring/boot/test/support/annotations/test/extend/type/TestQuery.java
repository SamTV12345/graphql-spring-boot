package com.graphql.spring.boot.test.support.annotations.test.extend.type;

import com.graphql.spring.boot.test.support.annotations.test.extend.type.model.BaseType;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLQueryResolver;

@GraphQLQueryResolver
public class TestQuery {

  @GraphQLField
  public static BaseType someValue() {
    return new BaseType("Test value");
  }
}
