package org.anton.vadarodtest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anton.vadarodtest.model.dto.CurrencyRateRequest;
import org.anton.vadarodtest.model.dto.CurrencyRateResponse;
import org.anton.vadarodtest.model.entity.CurrencyRate;
import org.anton.vadarodtest.repository.CurrencyRateRepository;
import org.anton.vadarodtest.service.CurrencyRateService;
import org.anton.vadarodtest.service.client.NbrbClient;
import org.anton.vadarodtest.service.exception.NbrbCurrencyRateNotFoundException;
import org.anton.vadarodtest.service.exception.ResourceNotFoundException;
import org.anton.vadarodtest.service.mapper.CurrencyRateMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;
    private final NbrbClient nbrbClient;
    private final CurrencyRateMapper currencyRateMapper;

    @Override
    public boolean loadRatesByDate(LocalDate date) {
        List<CurrencyRateRequest> rates = getCurrencyRateRequestsByDate(date);
        currencyRateRepository.saveAll(currencyRateMapper.toEntityList(rates));
        return true;
    }

    @Override
    public List<CurrencyRateResponse> getRatesByDate(LocalDate date) {
        return currencyRateMapper
                .toResponseList(getCurrencyRatesByDate(date));
    }

    @Override
    public CurrencyRateResponse getRateByDateAndCurAbbreviation(
            LocalDate date, String abbreviation) {
        return currencyRateMapper
                .toResponse(getCurrencyRateByDateAndAbbreviation(date, abbreviation));
    }

    private List<CurrencyRateRequest> getCurrencyRateRequestsByDate(LocalDate date) {
        List<CurrencyRateRequest> rates =
                nbrbClient.getRatesByDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 1);

        if (rates.isEmpty()) {
            throw new NbrbCurrencyRateNotFoundException(
                    HttpStatus.NOT_FOUND.value(), "Can't find currency by date = " + date);
        }

        return rates;
    }

    private List<CurrencyRate> getCurrencyRatesByDate(LocalDate date) {
        List<CurrencyRate> rates = currencyRateRepository.findByDate(date);
        if (rates.isEmpty()) {
            throw new ResourceNotFoundException(
                    HttpStatus.NOT_FOUND.value(),
                    "Can't find currency in db by date = "
                            + date + " please enter request to nbrb");
        }

        return rates;
    }

    private CurrencyRate getCurrencyRateByDateAndAbbreviation(
            LocalDate date, String abbreviation) {

        return currencyRateRepository.findByDateAndCurAbbreviation(date, abbreviation)
                .orElseThrow(() -> new ResourceNotFoundException(
                        HttpStatus.NOT_FOUND.value(),
                        "Can't find currency in db by date = " + date
                                + " and abbreviation = " + abbreviation
                                + " please enter request to nbrb"
                ));
    }

}
