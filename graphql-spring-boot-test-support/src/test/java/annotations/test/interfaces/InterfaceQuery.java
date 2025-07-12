package annotations.test.interfaces;

import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import graphql.kickstart.autoconfigure.annotations.test.interfaces.Car;
import graphql.kickstart.autoconfigure.annotations.test.interfaces.Truck;
import graphql.kickstart.autoconfigure.annotations.test.interfaces.Vehicle;
import java.util.Arrays;
import java.util.List;

@GraphQLQueryResolver
public class InterfaceQuery {

  @GraphQLField
  @GraphQLNonNull
  @GraphQLDescription("Returns vehicles")
  public static List<Vehicle> vehicles() {
    return Arrays.asList(new Car("ABC-123", 4), new Truck("CBA-321", 12));
  }
}
