package com.currencyapp.app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
class RateServiceTest {

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Mock
    private RateService rateService;

    @Test
    void isMapNotEmpty() {
        //Given
        String code = "eur";
        LocalDate dateOne = LocalDate.of(2017, 2, 11);
        LocalDate dateTwo = LocalDate.of(2017, 2, 13);
        Map<String, BigDecimal> mapToTest = rateService.getStandardDeviationAndAverageMap(code, dateOne, dateTwo);
        //When
        mapToTest.put("test", new BigDecimal(234.78));
        //Then
        assertFalse(mapToTest.isEmpty());
    }

    @Test
    void isMapContainingStringAsKey() {
        //Given
        String code = "eur";
        LocalDate dateOne = LocalDate.of(2017, 2, 11);
        LocalDate dateTwo = LocalDate.of(2017, 2, 13);
        Map<String, BigDecimal> map = rateService.getStandardDeviationAndAverageMap(code, dateOne, dateTwo);
        //When
        map.put("test", new BigDecimal(3453.54));
        //Then
        assertTrue(map.containsKey("test"));
    }

    @Test
    void areMapValuesNotNull() {
        //Given
        String code = "eur";
        LocalDate dateOne = LocalDate.of(2017, 2, 11);
        LocalDate dateTwo = LocalDate.of(2017, 2, 13);
        Map<String, BigDecimal> map = rateService.getStandardDeviationAndAverageMap(code, dateOne, dateTwo);
        //When
        map.put("test", new BigDecimal(342.654));
        //Then
        assertNotNull(map.values());
    }
}