package org.anton.vadarodtest.service.exception;

import lombok.Getter;
import org.anton.vadarodtest.model.dto.ErrorResponseTo;

@Getter
public class ResourceException extends RuntimeException{

    private final ErrorResponseTo errorResponseTo;

    public ResourceException(int code, String message) {
        super(message);
        errorResponseTo = new ErrorResponseTo(code, message, null);
    }
}
