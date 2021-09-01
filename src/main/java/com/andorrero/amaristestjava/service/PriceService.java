package com.andorrero.amaristestjava.service;

import java.util.Optional;

import com.andorrero.amaristestjava.model.dto.PriceEntity;

public interface PriceService {

    Optional<PriceEntity> finalPrice(final Integer brandId, final Integer productId, final Long applicationDate);

}
