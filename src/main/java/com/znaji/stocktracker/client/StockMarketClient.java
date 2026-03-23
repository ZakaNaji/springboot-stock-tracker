package com.znaji.stocktracker.client;

import com.znaji.stocktracker.model.StockHistory;
import com.znaji.stocktracker.model.StockOverview;
import com.znaji.stocktracker.model.StockQuote;

public interface StockMarketClient {

    StockQuote getStockQuote(String symbol);

    StockOverview getStockOverview(String symbol);

    StockHistory getStockHistory(String symbol, int days);
}
