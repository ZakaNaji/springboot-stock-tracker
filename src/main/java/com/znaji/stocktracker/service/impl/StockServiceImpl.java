package com.znaji.stocktracker.service.impl;

import com.znaji.stocktracker.client.StockMarketClient;
import com.znaji.stocktracker.model.StockHistory;
import com.znaji.stocktracker.model.StockOverview;
import com.znaji.stocktracker.model.StockQuote;
import com.znaji.stocktracker.service.StockService;
import org.jspecify.annotations.NonNull;
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
        var normalizedSymbol = getNormalizedSymbol(symbol);
        return stockMarketClient.getStockQuote(normalizedSymbol);
    }

    @Override
    @Cacheable(value = "stockOverviews", key = "#symbol.trim().toUpperCase()")
    public StockOverview getStockOverview(String symbol) {
        return stockMarketClient.getStockOverview(getNormalizedSymbol(symbol));
    }

    @Override
    @Cacheable(value = "stockHistories", key = "#symbol.trim().toUpperCase() + '_' + #days")
    public StockHistory getStockHistory(String symbol, int days) {
        return stockMarketClient.getStockHistory(getNormalizedSymbol(symbol), days);
    }


    private String getNormalizedSymbol(String symbol) {
        return symbol.trim().toUpperCase();
    }
}
