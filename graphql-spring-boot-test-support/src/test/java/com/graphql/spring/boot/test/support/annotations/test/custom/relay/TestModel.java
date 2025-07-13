package com.graphql.spring.boot.test.support.annotations.test.custom.relay;

import graphql.annotations.annotationTypes.GraphQLField;
import lombok.Value;

@Value
public class TestModel {

  @GraphQLField String someField;
}
