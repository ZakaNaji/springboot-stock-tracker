package com.znaji.stocktracker.controller;

import com.znaji.stocktracker.dto.response.StockQuoteResponse;
import com.znaji.stocktracker.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;


    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{symbol}")
    public StockQuoteResponse getStockQuote(@PathVariable String symbol) {
        return stockService.getStockQuote(symbol);
    }

}
