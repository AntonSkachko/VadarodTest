package org.anton.vadarodtest.service.exception;

import org.anton.vadarodtest.model.dto.ErrorResponseTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdviceException {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponseTo> catchThrowableException(
            final Throwable throwable) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(takeError(throwable, httpStatus));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseTo> catchResourceNotFoundException(
            ResourceNotFoundException exception) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(httpStatus)
                .body(takeError(exception));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseTo> catchMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(takeError(exception));
    }

    @ExceptionHandler(NbrbCurrencyRateNotFoundException.class)
    public ResponseEntity<ErrorResponseTo> catchNbrbCurrencyRateNotFoundException(
            NbrbCurrencyRateNotFoundException exception) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(takeError(exception));
    }

    private static ErrorResponseTo takeError(
            Throwable throwable, HttpStatus httpStatus) {

        return new ErrorResponseTo(
                httpStatus.value(),
                "Throwable exception",
                new String[]{throwable.getMessage()}
        );
    }

    private static ErrorResponseTo takeError(
            ResourceException exception) {

        return exception.getErrorResponseTo();
    }

    private static ErrorResponseTo takeError(
            MethodArgumentNotValidException exception) {
        List<String> messages = new ArrayList<>();

        for (ObjectError objectError : exception.getAllErrors()) {
            if (objectError instanceof FieldError fieldError) {
                messages.add("Field Error: " + fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                messages.add(objectError.toString());
            }
        }

        return new ErrorResponseTo(
            HttpStatus.BAD_REQUEST.value(),
                "MethodArgumentNotValidException exception",
                messages.toArray(String[]::new)
        );
    }
}
