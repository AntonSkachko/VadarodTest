package org.anton.vadarodtest.service;

import org.anton.vadarodtest.model.dto.CurrencyRateResponse;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyRateService {

    boolean loadRatesByDate(LocalDate date);
    List<CurrencyRateResponse> getRatesByDate(LocalDate date);
    CurrencyRateResponse getRateByDateAndCurAbbreviation(
            LocalDate date, String abbreviation);

}
