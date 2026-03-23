package com.znaji.stocktracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HistoricalPrice(
        LocalDate date,
        BigDecimal open,
        BigDecimal high,
        BigDecimal low,
        BigDecimal close,
        Long volume
) {
}
