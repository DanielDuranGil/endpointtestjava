package com.andorrero.endpointjava.controller;

import java.util.Optional;

import com.andorrero.endpointjava.model.dto.PriceEntity;
import com.andorrero.endpointjava.service.PriceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.andorrero.endpointjava.builder.ErrorResponseBuilder.buildErrorResponseDetail;
import static com.andorrero.endpointjava.error.PriceErrorCode.ERR_002;

@RestController
@RequestMapping("/")
public class PriceController {

    private final PriceService priceService;

    public PriceController(final PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/brand/{brandId}/product/{productId}/pvp")
    public ResponseEntity<Object> finalPrice(
            @PathVariable final Integer brandId,
            @PathVariable final Integer productId,
            @RequestParam final Long applicationDate) {
        final Optional<PriceEntity> price = priceService.finalPrice(brandId, productId, applicationDate);
        return price.isPresent() ? ResponseEntity.ok(price.get()) : ResponseEntity.ok(buildErrorResponseDetail(
                HttpStatus.OK.value(), ERR_002.getReasonPhrase()));
    }

}
