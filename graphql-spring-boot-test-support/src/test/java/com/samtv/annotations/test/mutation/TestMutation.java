package com.samtv.annotations.test.mutation;

import com.samtv.annotations.test.custom.relay.TestModel;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLMutationResolver;

@GraphQLMutationResolver
public class TestMutation {

  @GraphQLField
  public static TestModel performSomeOperation() {
    return new TestModel("Test value");
  }

  @GraphQLField
  public static TestModel performSomeOperationWithArgument(TestModel input) {
    return new TestModel("Test value");
  }
}
