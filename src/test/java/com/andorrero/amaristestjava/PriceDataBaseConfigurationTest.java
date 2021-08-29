package com.andorrero.amaristestjava;

import com.andorrero.amaristestjava.repository.PriceRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { AmarisTestJavaApplication.class, PriceDataBaseConfiguration.class })
class PriceDataBaseConfigurationTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void testWhenApplicationIsInitializedPriceTableHasValues() {
        assertThat(priceRepository.findAll()).hasSize(4);
    }

}
