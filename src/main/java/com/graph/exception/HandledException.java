package com.graph.exception;

import lombok.Data;

@Data
public class HandledException extends RuntimeException{

    private final Error error;

    public HandledException(Error error, String message) {
        super(message);
        this.error = error;
    }
}
