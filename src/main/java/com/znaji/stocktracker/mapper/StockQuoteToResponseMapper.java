package com.znaji.stocktracker.mapper;

import com.znaji.stocktracker.dto.response.StockQuoteResponse;
import com.znaji.stocktracker.model.StockQuote;
import org.springframework.stereotype.Component;

@Component
public class StockQuoteToResponseMapper {

    public StockQuoteResponse toResponse(StockQuote stockQuote) {
        if (stockQuote == null) {
            return null;
        }
        StockQuoteResponse response = new StockQuoteResponse();
        response.setSymbol(stockQuote.getSymbol());
        response.setCompanyName(stockQuote.getCompanyName());
        response.setPrice(stockQuote.getPrice());
        response.setChange(stockQuote.getChange());
        response.setChangePercent(stockQuote.getChangePercent());
        response.setDayLow(stockQuote.getDayLow());
        response.setDayHigh(stockQuote.getDayHigh());
        response.setOpen(stockQuote.getOpen());
        response.setPreviousClose(stockQuote.getPreviousClose());
        response.setVolume(stockQuote.getVolume());
        response.setQuoteTime(stockQuote.getQuoteTime());
        return response;
    }
}
