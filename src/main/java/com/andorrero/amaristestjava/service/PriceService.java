package com.andorrero.amaristestjava.service;

import java.util.Optional;

import com.andorrero.amaristestjava.model.dto.PriceEntity;

public interface PriceService {

    Optional<PriceEntity> finalPrice(
            final Long applicationDate,
            final Integer productId,
            final Integer brandId);

}
