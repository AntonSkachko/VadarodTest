package org.anton.vadarodtest.service.client;

import org.anton.vadarodtest.model.dto.CurrencyRateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "nbrbClient", url = "${nbrb.url}")
public interface NbrbClient {

    @GetMapping("/rates")
    List<CurrencyRateRequest> getRatesByDate(
            @RequestParam("ondate") String date,
            @RequestParam("periodicity") int periodicity);
}
