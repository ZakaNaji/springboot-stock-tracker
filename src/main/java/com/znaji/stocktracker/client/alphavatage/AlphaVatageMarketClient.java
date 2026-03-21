package com.znaji.stocktracker.client.alphavatage;

import com.znaji.stocktracker.client.StockMarketClient;
import com.znaji.stocktracker.model.StockQuote;
import org.springframework.stereotype.Service;

@Service
public class AlphaVatageMarketClient implements StockMarketClient {
    @Override
    public StockQuote getStockQuote(String symbol) {
        return null;
    }
}
