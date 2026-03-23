package com.znaji.stocktracker.model;

import java.util.List;

public record StockHistory(
        String symbol,
        int days,
        List<HistoricalPrice> prices
) {
}