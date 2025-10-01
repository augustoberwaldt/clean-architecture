package com.example.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundBusinessException extends RuntimeException{

    public NotFoundBusinessException(String message) {
        super(message);
    }

    public NotFoundBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
