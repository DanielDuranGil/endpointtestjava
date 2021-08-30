package com.andorrero.amaristestjava;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Map;

import com.andorrero.amaristestjava.builder.PriceEntityBuilder;
import com.andorrero.amaristestjava.model.dto.PriceEntity;
import com.andorrero.amaristestjava.repository.PriceRepository;

import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceRepository.save(new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                .priceList(1).productId(35455).priority(0).price(35.50f).currency("EUR").build()));
        priceRepository.save(new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.JUNE, 14, 18, 30, 59))
                .priceList(2).productId(35455).priority(1).price(25.45f).currency("EUR").build()));
        priceRepository.save(new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.JUNE, 15, 11, 0, 0))
                .priceList(3).productId(35455).priority(1).price(30.50f).currency("EUR").build()));
        priceRepository.save(new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 16, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                .priceList(4).productId(35455).priority(1).price(38.95f).currency("EUR").build()));
    }

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