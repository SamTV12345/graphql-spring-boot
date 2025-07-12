package annotations.test.custom.type.function;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import graphql.kickstart.autoconfigure.annotations.test.custom.type.function.Foo;

@GraphQLQueryResolver
public class TestQuery {

  @GraphQLField
  public static Foo foo() {
    return new Foo();
  }
}
