package com.andorrero.amaristestjava;

import java.time.LocalDateTime;
import java.time.Month;

import com.andorrero.amaristestjava.model.dto.PriceEntity;
import com.andorrero.amaristestjava.builder.PriceEntityBuilder;
import com.andorrero.amaristestjava.repository.PriceRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class PriceDataBaseConfiguration {

    private static final String LOG_INFO_SAVE_MESSAGE = "save price entity {}";

    @Bean
    CommandLineRunner initPriceTables(final PriceRepository priceRepository) {
        return args -> {
            log.info(LOG_INFO_SAVE_MESSAGE,
                    priceRepository.save(new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                            .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                            .priceList(1).productId(35455).priority(0).price(35.50f).currency("EUR").build())));
            log.info(LOG_INFO_SAVE_MESSAGE,
                    priceRepository.save(new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                            .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 15, 0, 0))
                            .endDate(LocalDateTime.of(2020, Month.JUNE, 14, 18, 30, 59))
                            .priceList(2).productId(35455).priority(1).price(25.45f).currency("EUR").build())));
            log.info(LOG_INFO_SAVE_MESSAGE,
                    priceRepository.save(new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                            .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 0, 0, 0))
                            .endDate(LocalDateTime.of(2020, Month.JUNE, 15, 11, 0, 0))
                            .priceList(3).productId(35455).priority(1).price(30.50f).currency("EUR").build())));
            log.info(LOG_INFO_SAVE_MESSAGE,
                    priceRepository.save(new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                            .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 16, 0, 0))
                            .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                            .priceList(4).productId(35455).priority(1).price(38.95f).currency("EUR").build())));
        };
    }

}
