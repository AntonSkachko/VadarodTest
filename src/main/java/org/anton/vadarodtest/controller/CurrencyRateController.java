package org.anton.vadarodtest.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.anton.vadarodtest.model.dto.CurrencyRateResponse;
import org.anton.vadarodtest.service.CurrencyRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rates")
@Tag(name = "Currency Rate controller")
public class CurrencyRateController {
    private final CurrencyRateService currencyRateService;

    @GetMapping
    @Operation(description = "Load currencies into db")
    public ResponseEntity<String> loadRates(
            @RequestParam @JsonFormat(pattern = "YYYY-MM-DD") LocalDate date) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(currencyRateService.loadRatesByDate(date) ?
                        "Data Loaded successfully" :
                        "Data not loaded");
    }


    @GetMapping("/{currencyCode}")
    @Operation(description = "Take currency from db")
    public ResponseEntity<CurrencyRateResponse> getRate(
            @RequestParam LocalDate date,
            @PathVariable String currencyCode) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(currencyRateService
                        .getRateByDateAndCurAbbreviation(date, currencyCode));
    }
}
