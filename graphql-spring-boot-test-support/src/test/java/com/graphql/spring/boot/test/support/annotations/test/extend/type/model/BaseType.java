package com.graphql.spring.boot.test.support.annotations.test.extend.type.model;

import graphql.annotations.annotationTypes.GraphQLField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseType {

  @GraphQLField private String baseTypeField;
}
