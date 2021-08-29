package com.andorrero.amaristestjava.error;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.andorrero.amaristestjava.builder.ErrorResponseBuilder.buildErrorResponseDetail;
import static com.andorrero.amaristestjava.error.PriceErrorCode.ERR_001;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class PriceExceptionHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            final MissingServletRequestParameterException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        var errorResponseDetail = buildErrorResponseDetail(HttpStatus.BAD_REQUEST.value(), ERR_001.getReasonPhrase());
        log.error("Handle Missing request parameters: {}", errorResponseDetail);
        return handleExceptionInternal(ex, errorResponseDetail, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
