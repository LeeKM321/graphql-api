package com.codeit.graphql.exception;

import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;

@ControllerAdvice
public class GraphQLExceptionHandler {

    // GraphQL의 예외처리는 리턴 타입을 GraphQLError로 세팅하고
    // 추가 정보가 필요하다면 extensions에 Map으로 전달하면 됩니다.
    @GraphQlExceptionHandler
    public GraphQLError handleNotFoundException(BookNotFoundException e) {
        return GraphQLError.newError()
                .message(e.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .extensions(Map.of(
                        "errorCode", "ERR-BOOK-001",
                        "httpStatus", "404"
                ))
                .build();
    }

}
