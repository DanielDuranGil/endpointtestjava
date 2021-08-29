package com.andorrero.amaristestjava.builder;

import java.time.LocalDateTime;

import com.andorrero.amaristestjava.model.domain.ErrorResponseDetail;

public class ErrorResponseBuilder {

    private ErrorResponseBuilder() {
    }

    public static ErrorResponseDetail buildErrorResponseDetail(final Integer code, final String description) {
        return ErrorResponseDetail.builder().date(LocalDateTime.now()).code(code)
                .description(description).build();
    }

}
