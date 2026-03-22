package com.znaji.stocktracker.exception;

import com.znaji.stocktracker.exception.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StockProviderException.class)
    public ResponseEntity<ApiErrorResponse> handleStockProviderException(StockProviderException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;
        return ResponseEntity.status(status)
                .body(buildErrorResponse(status, e.getMessage(), request));
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleStockNotFoundException(StockNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status)
                .body(buildErrorResponse(status, e.getMessage(), request));
    }


    private ApiErrorResponse buildErrorResponse(
            HttpStatus status,
            String message,
            HttpServletRequest request
    ) {
        return new ApiErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        );
    }
}
