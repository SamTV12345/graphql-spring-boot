package com.graphql.spring.boot.test.support.annotations.test.subscription;

import graphql.annotations.annotationTypes.GraphQLDataFetcher;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLSubscriptionResolver;

@GraphQLSubscriptionResolver
public class TestSubscription {

  @GraphQLField
  @GraphQLDataFetcher(TestDataFetcher.class)
  public static String testSubscription() {
    return null;
  }
}
