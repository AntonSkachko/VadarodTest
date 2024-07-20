package org.anton.vadarodtest.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateRequest {
    @JsonProperty("Cur_Id")
    private long curId;
    @JsonProperty("Date")
    private LocalDate date;
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;
    @JsonProperty("Cur_Scale")
    private long curScale;
    @JsonProperty("Cur_Name")
    private String curName;
    @JsonProperty("Cur_OfficialRate")
    private double curOfficialRate;
}
