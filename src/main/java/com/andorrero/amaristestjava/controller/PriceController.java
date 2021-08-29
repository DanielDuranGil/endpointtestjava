package com.andorrero.amaristestjava.controller;

import java.util.Optional;

import com.andorrero.amaristestjava.model.dto.PriceEntity;
import com.andorrero.amaristestjava.service.PriceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.andorrero.amaristestjava.builder.ErrorResponseBuilder.buildErrorResponseDetail;
import static com.andorrero.amaristestjava.error.PriceErrorCode.ERR_002;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(final PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/pvp")
    public ResponseEntity<Object> finalPrice(
            @RequestParam final Long applicationDate,
            @RequestParam final Integer productId,
            @RequestParam final Integer brandId) {
        final Optional<PriceEntity> price = priceService.finalPrice(applicationDate, productId, brandId);
        return price.isPresent() ? ResponseEntity.ok(price.get()) : ResponseEntity.ok(buildErrorResponseDetail(
                HttpStatus.OK.value(), ERR_002.getReasonPhrase()));
    }

}
