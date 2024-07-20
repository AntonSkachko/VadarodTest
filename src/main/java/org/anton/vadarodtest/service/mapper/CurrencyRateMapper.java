package org.anton.vadarodtest.service.mapper;

import org.anton.vadarodtest.model.dto.CurrencyRateRequest;
import org.anton.vadarodtest.model.dto.CurrencyRateResponse;
import org.anton.vadarodtest.model.entity.CurrencyRate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrencyRateMapper {

    CurrencyRateResponse toResponse(CurrencyRate currencyRate);
    CurrencyRate toEntity(CurrencyRateRequest currencyRateRequest);
    List<CurrencyRate> toEntityList(List<CurrencyRateRequest> currencyRateRequestList);
    List<CurrencyRateResponse> toResponseList(List<CurrencyRate> currencyRateList);
}
