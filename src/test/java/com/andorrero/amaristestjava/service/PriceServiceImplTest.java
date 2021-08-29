package com.andorrero.amaristestjava.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Optional;

import com.andorrero.amaristestjava.model.dto.PriceEntity;
import com.andorrero.amaristestjava.builder.PriceEntityBuilder;
import com.andorrero.amaristestjava.repository.PriceRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PriceServiceImplTest {

    private PriceService priceService;

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceService = new PriceServiceImpl(priceRepository);
    }

    @Test
    void testWhenNoDataInTableShouldBePvpNotFound() {
        final Optional<PriceEntity> priceEntityOptional =
                priceService.finalPrice(
                        LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),
                        1, 1);

        assertThat(priceEntityOptional).isEmpty();
    }

    @Test
    void testWhenProductNotExistsShouldGetPvpNotFound() {
        priceRepository.save(createPriceEntity(0));
        final Optional<PriceEntity> priceEntityOptional =
                priceService.finalPrice(
                        LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),
                        1, 1);

        assertThat(priceEntityOptional).isEmpty();
    }

    @Test
    void testWhenBrandNotExistsShouldGetPvpNotFound() {
        priceRepository.save(createPriceEntity(0));
        final Optional<PriceEntity> priceEntityOptional =
                priceService.finalPrice(
                        LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),
                        35455, 2);

        assertThat(priceEntityOptional).isEmpty();
    }

    @Test
    void testWhenApplicationDateNotIsInPeriodShouldGetPvpNotFound() {
        priceRepository.save(createPriceEntity(0));
        final Optional<PriceEntity> priceEntityOptional =
                priceService.finalPrice(
                        LocalDateTime.of(2021, Month.JUNE, 14, 10, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),
                        35455, 1);

        assertThat(priceEntityOptional).isEmpty();
    }

    @Test
    void testWhenOnePvpExistsForInputDataShouldGetPvp() {
        final PriceEntity priceEntityTest = createPriceEntity(0);
        priceRepository.save(priceEntityTest);
        final Optional<PriceEntity> priceEntityOptional =
                priceService.finalPrice(
                        LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),
                        35455, 1);

        assertThat(priceEntityOptional).isPresent();
        assertThat(priceEntityTest).isEqualTo(priceEntityOptional.get());
    }

    @Test
    void testWhenSeveralPvpExistsForInputDataShouldGetPvpOrderByPriority() {
        final PriceEntity priceEntityTestPriority1 = createPriceEntity(1);
        priceRepository.save(createPriceEntity(0));
        priceRepository.save(priceEntityTestPriority1);
        final Optional<PriceEntity> priceEntityOptional =
                priceService.finalPrice(
                        LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond(),
                        35455, 1);

        assertThat(priceEntityOptional).isPresent();
        assertThat(priceEntityTestPriority1).isEqualTo(priceEntityOptional.get());
    }

    private PriceEntity createPriceEntity(final Integer priority) {
        return new PriceEntity(PriceEntityBuilder.builder().brandId(1)
                .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
                .priceList(1).productId(35455).priority(priority).price(35.50f).currency("EUR").build());
    }

}
