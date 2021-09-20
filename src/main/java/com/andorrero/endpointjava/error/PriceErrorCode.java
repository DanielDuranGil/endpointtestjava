package com.andorrero.endpointjava.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PriceErrorCode implements ErrorCode {

    ERR_001("Impossible to find all the query parameters in the petition"),

    ERR_002("No prices have been found for that product, brand and date");

    private final String reasonPhrase;

}
