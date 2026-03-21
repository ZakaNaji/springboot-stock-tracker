package com.znaji.stocktracker.controller;

import com.znaji.stocktracker.dto.response.StockQuoteResponse;
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
    private final StockQuoteToResponseMapper mapper;


    public StockController(StockService stockService, StockQuoteToResponseMapper mapper) {
        this.stockService = stockService;
        this.mapper = mapper;
    }

    @GetMapping("/{symbol}")
    public StockQuoteResponse getStockQuote(@PathVariable String symbol) {
        return mapper.toResponse(stockService.getStockQuote(symbol));
    }

}
