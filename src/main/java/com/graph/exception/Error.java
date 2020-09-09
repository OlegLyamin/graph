package com.graph.exception;

import com.graph.model.entity.Edge;
import com.graph.model.entity.Graph;
import lombok.Getter;
import org.zalando.problem.Status;

public enum Error {

    VALIDATION_ERROR,
    EDGE_NOT_FOUND(Status.NOT_FOUND, ErrorType.NOT_FOUND, Edge.class),
    GRAPH_NOT_FOUND(Status.NOT_FOUND, ErrorType.NOT_FOUND, Graph.class);

    @Getter
    private Status status;

    @Getter
    private ErrorType errorType;

    @Getter
    private Class<?> entityClass;

    Error(){

    }

    Error(Status status, ErrorType errorType, Class<?> entityClass){
        this.status = status;
        this.errorType = errorType;
        this.entityClass = entityClass;
    }
}
