package com.andorrero.amaristestjava.model.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.andorrero.amaristestjava.builder.PriceEntityBuilder;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
@JsonIgnoreProperties(value = { "id", "priority", "currency" })
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Column(name = "START_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST")
    private Integer priceList;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "CURR")
    private String currency;

    public PriceEntity(final PriceEntityBuilder priceEntityBuilder) {
        this.brandId = priceEntityBuilder.getBrandId();
        this.startDate = priceEntityBuilder.getStartDate();
        this.endDate = priceEntityBuilder.getEndDate();
        this.priceList = priceEntityBuilder.getPriceList();
        this.productId = priceEntityBuilder.getProductId();
        this.priority = priceEntityBuilder.getPriority();
        this.price = priceEntityBuilder.getPrice();
        this.currency = priceEntityBuilder.getCurrency();
    }

}
