package com.znaji.stocktracker.client.fmp.mapper;

import com.znaji.stocktracker.client.fmp.dto.FmpQuoteResponse;
import com.znaji.stocktracker.exception.StockNotFoundException;
import com.znaji.stocktracker.model.StockQuote;
import org.springframework.stereotype.Component;

import static com.znaji.stocktracker.utils.Helpers.*;

@Component
public class FmpQuoteMapper {

    public StockQuote toStockQuote(FmpQuoteResponse response) {
        if (response == null || isBlank(response.getSymbol())) {
            throw new StockNotFoundException("No stock quote found");
        }

        return new StockQuote(
                response.getSymbol(),
                response.getName(),
                toBigDecimal(response.getPrice()),
                toBigDecimal(response.getChange()),
                toBigDecimal(response.getChangePercentage()),
                toBigDecimal(response.getDayLow()),
                toBigDecimal(response.getDayHigh()),
                toBigDecimal(response.getOpen()),
                toBigDecimal(response.getPreviousClose()),
                response.getVolume(),
                toLocalDateTime(response.getTimestamp())
        );
    }
}
