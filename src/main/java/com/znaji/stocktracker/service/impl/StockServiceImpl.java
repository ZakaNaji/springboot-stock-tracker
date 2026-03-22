package com.znaji.stocktracker.service.impl;

import com.znaji.stocktracker.client.StockMarketClient;
import com.znaji.stocktracker.model.StockQuote;
import com.znaji.stocktracker.service.StockService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private final StockMarketClient stockMarketClient;

    public StockServiceImpl(StockMarketClient stockMarketClient) {
        this.stockMarketClient = stockMarketClient;
    }

    @Override
    @Cacheable(value = "stockQuotes", key = "#symbol.trim().toUpperCase()")
    public StockQuote getStockQuote(String symbol) {
        var normalizedSymbol = symbol.trim().toUpperCase();
        return stockMarketClient.getStockQuote(normalizedSymbol);
    }
}
