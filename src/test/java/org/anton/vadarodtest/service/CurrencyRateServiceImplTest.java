package org.anton.vadarodtest.service;

import org.anton.vadarodtest.model.dto.CurrencyRateRequest;
import org.anton.vadarodtest.model.entity.CurrencyRate;
import org.anton.vadarodtest.repository.CurrencyRateRepository;
import org.anton.vadarodtest.service.client.NbrbClient;
import org.anton.vadarodtest.service.impl.CurrencyRateServiceImpl;
import org.anton.vadarodtest.service.mapper.CurrencyRateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CurrencyRateServiceImplTest {

    @Mock
    private CurrencyRateRepository currencyRateRepository;

    @Mock
    private NbrbClient nbrbClient;

    @Mock
    private CurrencyRateMapper currencyRateMapper;

    @InjectMocks
    private CurrencyRateServiceImpl currencyRateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadRatesByDate() {
        LocalDate date = LocalDate.now();
        List<CurrencyRateRequest> rates = Collections.singletonList(new CurrencyRateRequest());
        when(nbrbClient.getRatesByDate(eq(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))), eq(1)))
                .thenReturn(rates);

        currencyRateService.loadRatesByDate(date);

        verify(currencyRateRepository).saveAll(currencyRateMapper.toEntityList(rates));
    }

    @Test
    void testGetRatesByDate() {
        LocalDate date = LocalDate.now();
        List<CurrencyRate> currencyRates = Collections.singletonList(new CurrencyRate());
        when(currencyRateRepository.findByDate(eq(date))).thenReturn(currencyRates);

        currencyRateService.getRatesByDate(date);

        verify(currencyRateMapper).toResponseList(currencyRates);
    }

    @Test
    void testGetRateByDateAndCurAbbreviation() {
        LocalDate date = LocalDate.now();
        String abbreviation = "USD";
        CurrencyRate currencyRate = new CurrencyRate();
        when(currencyRateRepository.findByDateAndCurAbbreviation(eq(date), eq(abbreviation)))
                .thenReturn(Optional.of(currencyRate));

        currencyRateService.getRateByDateAndCurAbbreviation(date, abbreviation);

        verify(currencyRateMapper).toResponse(currencyRate);
    }
}
