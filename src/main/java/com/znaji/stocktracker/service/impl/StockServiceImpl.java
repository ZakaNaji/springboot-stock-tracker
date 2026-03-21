package com.znaji.stocktracker.service.impl;

import com.znaji.stocktracker.client.StockMarketClient;
import com.znaji.stocktracker.dto.response.StockQuoteResponse;
import com.znaji.stocktracker.mapper.StockQuoteToResponseMapper;
import com.znaji.stocktracker.service.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private final StockMarketClient stockMarketClient;
    private final StockQuoteToResponseMapper quoteMapper;

    public StockServiceImpl(StockMarketClient stockMarketClient, StockQuoteToResponseMapper quoteMapper) {
        this.stockMarketClient = stockMarketClient;
        this.quoteMapper = quoteMapper;
    }

    @Override
    public StockQuoteResponse getStockQuote(String symbol) {
        return quoteMapper.toResponse(stockMarketClient.getStockQuote(symbol));
    }
}
