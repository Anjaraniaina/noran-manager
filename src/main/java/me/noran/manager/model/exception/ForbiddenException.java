package me.noran.manager.model.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ServerException {
    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN.value());
    }
}
