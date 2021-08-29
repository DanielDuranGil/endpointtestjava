package com.andorrero.amaristestjava.repository;

import java.util.List;

import com.andorrero.amaristestjava.model.dto.PriceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    List<PriceEntity> findByBrandIdAndProductId(final Integer brandId, final Integer productId);

}
