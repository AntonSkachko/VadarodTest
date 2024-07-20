package org.anton.vadarodtest.service.exception;

public class NbrbCurrencyRateNotFoundException extends ResourceException{

    public NbrbCurrencyRateNotFoundException(int code, String message) {
        super(code, message);
    }
}
