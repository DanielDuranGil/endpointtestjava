package com.andorrero.endpointjava.repository;

import java.util.List;

import com.andorrero.endpointjava.model.dto.PriceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    List<PriceEntity> findByBrandIdAndProductId(final Integer brandId, final Integer productId);

}
