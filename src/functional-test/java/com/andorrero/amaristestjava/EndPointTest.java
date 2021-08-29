package com.andorrero.amaristestjava;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AmarisTestJavaApplication.class)
class EndPointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @CsvSource({
            "14, 10, 1",
            "14, 16, 2",
            "14, 21, 1",
            "15, 10, 3",
            "16, 21, 4"
    })
    void testGivenSpecificPetitionShouldBeTheCorrectPriceList(
            Integer day,
            Integer hour,
            Integer priceList) {
        final String applicationDate = String.valueOf(
                LocalDateTime.of(2020, Month.JUNE, day, hour, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond());
        ResponseEntity<Object> response = restTemplate
                .getForEntity("/prices/pvp?applicationDate=" + applicationDate + "&productId=35455&brandId=1",
                        Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((Map) response.getBody())).containsEntry("priceList", priceList);
    }

}