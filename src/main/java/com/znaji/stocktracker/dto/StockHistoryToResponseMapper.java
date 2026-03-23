package com.znaji.stocktracker.dto;

import com.znaji.stocktracker.dto.response.HistoricalPriceResponse;
import com.znaji.stocktracker.dto.response.StockHistoryResponse;
import com.znaji.stocktracker.model.StockHistory;
import org.springframework.stereotype.Component;

@Component
public class StockHistoryToResponseMapper {
    public StockHistoryResponse toResponse(StockHistory stockHistory) {
        var priceResponses = stockHistory.prices().stream()
                .map(p -> new HistoricalPriceResponse(
                        p.date(),
                        p.open(),
                        p.high(),
                        p.low(),
                        p.close(),
                        p.volume()
                ))
                .toList();

        return new StockHistoryResponse(
                stockHistory.symbol(),
                stockHistory.days(),
                priceResponses
        );
    }
}
