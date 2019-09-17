package com.currencyapp.app.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RateServiceTest {

    @Autowired
    private RateService rateService;

    @Test
    void isMapNotEmpty() {
        //Given
        final String code = "eur";
        final LocalDate dateOne = LocalDate.of(2017, 2, 11);
        final LocalDate dateTwo = LocalDate.of(2017, 2, 13);
        //When
        final Map<String, BigDecimal> mapToTest = rateService.getStandardDeviationAndAverageMap(code, dateOne, dateTwo);
        //Then
        assertFalse(mapToTest.isEmpty());
    }

    @Test
    void areMapValuesNotNull() {
        //Given
        final String code = "eur";
        final LocalDate dateOne = LocalDate.of(2017, 2, 11);
        final LocalDate dateTwo = LocalDate.of(2017, 2, 13);
        //When
        final Map<String, BigDecimal> map = rateService.getStandardDeviationAndAverageMap(code, dateOne, dateTwo);
        //Then
        assertNotNull(map.values());
    }
}