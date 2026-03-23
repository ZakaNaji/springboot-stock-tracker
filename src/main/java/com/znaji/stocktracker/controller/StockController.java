package com.znaji.stocktracker.controller;

import com.znaji.stocktracker.dto.StockHistoryToResponseMapper;
import com.znaji.stocktracker.dto.response.StockHistoryResponse;
import com.znaji.stocktracker.dto.response.StockOverviewResponse;
import com.znaji.stocktracker.dto.response.StockQuoteResponse;
import com.znaji.stocktracker.mapper.StockOverviewToResponseMapper;
import com.znaji.stocktracker.mapper.StockQuoteToResponseMapper;
import com.znaji.stocktracker.model.StockHistory;
import com.znaji.stocktracker.service.StockService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;
    private final StockQuoteToResponseMapper stockQuoteToResponseMapper;
    private final StockOverviewToResponseMapper stockOverviewToResponseMapper;
    private final StockHistoryToResponseMapper stockHistoryToResponseMapper;


    public StockController(StockService stockService, StockQuoteToResponseMapper stockQuoteToResponseMapper, StockOverviewToResponseMapper stockOverviewToResponseMapper, StockHistoryToResponseMapper stockHistoryToResponseMapper) {
        this.stockService = stockService;
        this.stockQuoteToResponseMapper = stockQuoteToResponseMapper;
        this.stockOverviewToResponseMapper = stockOverviewToResponseMapper;
        this.stockHistoryToResponseMapper = stockHistoryToResponseMapper;
    }

    @GetMapping("/{symbol}")
    public StockQuoteResponse getStockQuote(@PathVariable String symbol) {
        return stockQuoteToResponseMapper.toResponse(stockService.getStockQuote(symbol));
    }

    @GetMapping("/{symbol}/overview")
    public StockOverviewResponse getStockOverview(@PathVariable String symbol) {
        return stockOverviewToResponseMapper.toResponse(stockService.getStockOverview(symbol));
    }
    
    @GetMapping("/{symbol}/history")
    public StockHistoryResponse getStockHistory(@PathVariable String symbol,
                                                @Valid @Min(1) @Max(365) @RequestParam(defaultValue = "30") int days) {

        return stockHistoryToResponseMapper.toResponse(stockService.getStockHistory(symbol, days));
    }

}
