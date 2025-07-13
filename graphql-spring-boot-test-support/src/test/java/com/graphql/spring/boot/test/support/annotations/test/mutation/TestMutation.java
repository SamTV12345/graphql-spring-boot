package com.graphql.spring.boot.test.support.annotations.test.mutation;

import com.graphql.spring.boot.test.support.annotations.test.custom.relay.TestModel;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLMutationResolver;

@GraphQLMutationResolver
public class TestMutation {

  @GraphQLField
  public static TestModel performSomeOperation() {
    System.out.printf("Called!!");
    return new TestModel("Test value");
  }

  @GraphQLField
  public static TestModel performSomeOperationWithArgument(TestModel input) {
    System.out.printf("Called!!");
    return new TestModel("Test value");
  }
}
