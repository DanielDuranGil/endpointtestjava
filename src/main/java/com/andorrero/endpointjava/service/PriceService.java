package com.andorrero.endpointjava.service;

import java.util.Optional;

import com.andorrero.endpointjava.model.dto.PriceEntity;

public interface PriceService {

    Optional<PriceEntity> finalPrice(final Integer brandId, final Integer productId, final Long applicationDate);

}
