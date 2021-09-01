package com.andorrero.amaristestjava.controller;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import com.andorrero.amaristestjava.builder.PriceEntityBuilder;
import com.andorrero.amaristestjava.model.domain.ErrorResponseDetail;
import com.andorrero.amaristestjava.model.dto.PriceEntity;
import com.andorrero.amaristestjava.repository.PriceRepository;
import com.andorrero.amaristestjava.service.PriceService;
import com.andorrero.amaristestjava.service.PriceServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.andorrero.amaristestjava.error.PriceErrorCode.ERR_002;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PriceControllerTest {

    private PriceController priceController;

    private PriceService priceService;

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceService = new PriceServiceImpl(priceRepository);
        priceController = new PriceController(priceService);
    }

    @Test
    void testWhenPvpNotFoundShouldBeOkStatusAndNotFoundErrorMessage() {
        final ResponseEntity<Object> responseEntity =
                priceController.finalPrice(1, 1,
                        LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0).atZone(ZoneId.systemDefault())
                                .toEpochSecond());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((ErrorResponseDetail) responseEntity.getBody()).getCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(((ErrorResponseDetail) responseEntity.getBody()).getDescription())
                .isEqualTo(ERR_002.getReasonPhrase());
    }

    @Test
    void testWhenPvpIsFoundShouldBeOkStatusAndPriceEntityData() {
        final PriceEntity priceEntityTest = new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                .priceList(1).productId(35455).priority(0).price(35.50f).currency("EUR").build());
        priceRepository.save(priceEntityTest);
        final ResponseEntity<Object> responseEntity =
                priceController.finalPrice(1, 35455,
                        LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0).atZone(ZoneId.systemDefault())
                                .toEpochSecond());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(priceEntityTest);
    }

}
