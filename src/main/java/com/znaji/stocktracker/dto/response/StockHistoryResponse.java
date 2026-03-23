package com.znaji.stocktracker.dto.response;

import java.util.List;

public record StockHistoryResponse(
        String symbol,
        int days,
        List<HistoricalPriceResponse> prices
) {
}