package com.graphql.spring.boot.test.support.annotations.test.interfaces;

import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import java.util.ArrayList;
import java.util.List;

@GraphQLQueryResolver
public class InterfaceQuery {

  @GraphQLField
  @GraphQLNonNull
  @GraphQLDescription("Returns vehicles")
  public static List<Vehicle> vehicles() {
    var car = new Car("ABC-123", 4);
    var truck = new Truck("CBA-321", 12);
    List<Vehicle> vehicles = new ArrayList<>();
    vehicles.add(car);
    vehicles.add(truck);
    return vehicles;
  }
}
