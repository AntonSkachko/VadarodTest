package org.anton.vadarodtest.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CurrencyRateResponse {
    private long curId;
    private LocalDate date;
    private String curAbbreviation;
    private long curScale;
    private String curName;
    private double curOfficialRate;
}
