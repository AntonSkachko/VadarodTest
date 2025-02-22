package org.anton.vadarodtest.repository;

import org.anton.vadarodtest.model.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    List<CurrencyRate> findByDate(LocalDate date);
    Optional<CurrencyRate> findByDateAndCurAbbreviation(LocalDate date, String abbreviation);
}
