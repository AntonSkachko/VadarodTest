package org.anton.vadarodtest.model.dto;

public record ErrorResponseTo(
        int code, String message, String[] errorsMessage) {
}
