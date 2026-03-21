package com.znaji.stocktracker.service;

import com.znaji.stocktracker.model.StockQuote;

public interface StockService {
    StockQuote getStockQuote(String symbol);
}
