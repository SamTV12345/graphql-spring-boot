package annotations.test.directive;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import graphql.kickstart.autoconfigure.annotations.test.directive.UpperCaseDirective;

@GraphQLQueryResolver
public class TestQueryWithDirective {

  @GraphQLField
  @UpperCaseDirective
  public static String queryWithDirective() {
    return "this should be uppercase";
  }
}
