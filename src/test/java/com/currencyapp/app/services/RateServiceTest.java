package com.currencyapp.app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class RateServiceTest {

    private RateService rateService;

    @BeforeEach
    void setUp() {
        rateService = mock(RateService.class);
    }

    @Test
    void isMapBeingUpdatedProperly() {
        //Given
        String code = "eur";
        LocalDate dateOne = LocalDate.of(2017, 02, 11);
        LocalDate dateTwo = LocalDate.of(2017, 02, 13);
        //When
        Map<String, Double> mapToTest = rateService.getStandardDeviationAndAverageMap(code, dateOne, dateTwo);
        //Then
        assertNotNull(mapToTest);
    }

    @Test
    void isMapsKeyString() {
        //Given
        String code = "eur";
        LocalDate dateOne = LocalDate.of(2017, 02, 11);
        LocalDate dateTwo = LocalDate.of(2017, 02, 13);
        //When
        Set<String> keys = new HashSet<>();
        //Then
        assertEquals(rateService.getStandardDeviationAndAverageMap(code, dateOne, dateTwo).keySet(), keys);
    }

    @Test
    void areMapValuesNotNull() {
        //Given
        String code = "eur";
        LocalDate dateOne = LocalDate.of(2017, 02, 11);
        LocalDate dateTwo = LocalDate.of(2017, 02, 13);
        //When
        Map<String, Double> map = rateService.getStandardDeviationAndAverageMap(code, dateOne, dateTwo);
        //Then
        assertNotNull(map.values());

    }
}