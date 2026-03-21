package com.znaji.stocktracker.service;

import com.znaji.stocktracker.dto.response.StockQuoteResponse;

public interface StockService {
    StockQuoteResponse getStockQuote(String symbol);
}
