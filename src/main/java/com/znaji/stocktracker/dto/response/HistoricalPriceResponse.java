package com.znaji.stocktracker.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HistoricalPriceResponse(
        LocalDate date,
        BigDecimal open,
        BigDecimal high,
        BigDecimal low,
        BigDecimal close,
        Long volume
) {
}