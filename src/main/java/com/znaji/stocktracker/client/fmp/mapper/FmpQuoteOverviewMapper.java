package com.znaji.stocktracker.client.fmp.mapper;

import com.znaji.stocktracker.client.fmp.dto.FmpQuoteOverviewResponse;
import com.znaji.stocktracker.exception.StockProviderException;
import com.znaji.stocktracker.model.StockOverview;

import static com.znaji.stocktracker.utils.Helpers.toBigDecimal;

public class FmpQuoteOverviewMapper {

    public StockOverview toStockOverview(FmpQuoteOverviewResponse response) {
        if (response == null || response.getSymbol() == null) {
            throw new StockProviderException("No stock overview found");
        }

        return null;//TODO: Implement mapping logic based on FmpQuoteOverviewResponse fields and StockOverview constructor
    }
}
