package com.andorrero.amaristestjava.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.andorrero.amaristestjava.model.dto.PriceEntity;
import com.andorrero.amaristestjava.repository.PriceRepository;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(final PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<PriceEntity> finalPrice(
            final Long applicationDate,
            final Integer productId,
            final Integer brandId) {
        log.info("Get final Price for date {}, product {}, brand {}", applicationDate, productId, brandId);

        var applicationDateInLocalDateTime = LocalDateTime
                .ofInstant(Instant.ofEpochSecond(applicationDate), ZoneId.systemDefault());

        final Optional<List<PriceEntity>> priceList = Optional.ofNullable(priceRepository
                .findByBrandIdAndProductId(brandId, productId));

        if (priceList.isPresent()) {
            return priceList.get().stream().filter(priceEntity ->
                    (applicationDateInLocalDateTime.isAfter(priceEntity.getStartDate())
                            || applicationDateInLocalDateTime
                            .isEqual(priceEntity.getStartDate()))
                            &&
                            (applicationDateInLocalDateTime.isBefore(priceEntity.getEndDate())
                                    || applicationDateInLocalDateTime
                                    .isEqual(priceEntity.getEndDate())))
                    .collect(Collectors.toList())
                    .stream()
                    .sorted(Comparator.comparingInt(PriceEntity::getPriority).reversed()).findFirst();
        } else {
            return Optional.empty();
        }
    }

}
