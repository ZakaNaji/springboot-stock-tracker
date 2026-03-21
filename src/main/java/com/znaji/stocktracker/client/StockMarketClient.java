package com.znaji.stocktracker.client;

import com.znaji.stocktracker.model.StockQuote;

public interface StockMarketClient {

    StockQuote getStockQuote(String symbol);
}
