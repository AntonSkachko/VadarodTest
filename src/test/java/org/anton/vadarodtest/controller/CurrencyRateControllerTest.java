package org.anton.vadarodtest.controller;
import org.anton.vadarodtest.model.dto.CurrencyRateResponse;
import org.anton.vadarodtest.service.CurrencyRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CurrencyRateControllerTest {

    @Mock
    private CurrencyRateService currencyRateService;

    @InjectMocks
    private CurrencyRateController currencyRateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadRates() {
        LocalDate date = LocalDate.now();
        when(currencyRateService.loadRatesByDate(eq(date))).thenReturn(true);

        ResponseEntity<String> response = currencyRateController.loadRates(date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Data Loaded successfully", response.getBody());
    }

    @Test
    void testGetRate() {
        LocalDate date = LocalDate.now();
        String currencyCode = "USD";
        CurrencyRateResponse currencyRateResponse = new CurrencyRateResponse();
        when(currencyRateService.getRateByDateAndCurAbbreviation(eq(date), eq(currencyCode)))
                .thenReturn(currencyRateResponse);

        ResponseEntity<CurrencyRateResponse> response = currencyRateController.getRate(date, currencyCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(currencyRateResponse, response.getBody());
    }
}
