package com.znaji.stocktracker.controller;

import com.znaji.stocktracker.dto.response.StockOverviewResponse;
import com.znaji.stocktracker.dto.response.StockQuoteResponse;
import com.znaji.stocktracker.mapper.StockOverviewToResponseMapper;
import com.znaji.stocktracker.mapper.StockQuoteToResponseMapper;
import com.znaji.stocktracker.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;
    private final StockQuoteToResponseMapper stockQuoteToResponseMapper;
    private final StockOverviewToResponseMapper stockOverviewToResponseMapper;


    public StockController(StockService stockService, StockQuoteToResponseMapper stockQuoteToResponseMapper, StockOverviewToResponseMapper stockOverviewToResponseMapper) {
        this.stockService = stockService;
        this.stockQuoteToResponseMapper = stockQuoteToResponseMapper;
        this.stockOverviewToResponseMapper = stockOverviewToResponseMapper;
    }

    @GetMapping("/{symbol}")
    public StockQuoteResponse getStockQuote(@PathVariable String symbol) {
        return stockQuoteToResponseMapper.toResponse(stockService.getStockQuote(symbol));
    }

    @GetMapping("/{symbol}/overview")
    public StockOverviewResponse getStockOverview(@PathVariable String symbol) {
        return stockOverviewToResponseMapper.toResponse(stockService.getStockOverview(symbol));
    }

}
