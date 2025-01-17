package me.noran.manager.model.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ServerException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
