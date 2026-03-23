
package com.znaji.stocktracker.dto.response;

import java.math.BigDecimal;

public record StockOverviewResponse(
        String symbol,
        String companyName,
        String description,
        String exchange,
        String currency,
        String country,
        String sector,
        String industry,
        String officialSite,
        BigDecimal marketCapitalization,
        BigDecimal peRatio,
        BigDecimal eps,
        BigDecimal fiftyTwoWeekHigh,
        BigDecimal fiftyTwoWeekLow
) {
}