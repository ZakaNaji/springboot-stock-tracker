package com.znaji.stocktracker.mapper;

import com.znaji.stocktracker.dto.response.StockOverviewResponse;
import com.znaji.stocktracker.model.StockOverview;
import org.springframework.stereotype.Component;

@Component
public class StockOverviewToResponseMapper {

    public StockOverviewResponse toResponse(StockOverview overview) {
        return new StockOverviewResponse(
                overview.symbol(),
                overview.companyName(),
                overview.description(),
                overview.exchange(),
                overview.currency(),
                overview.country(),
                overview.sector(),
                overview.industry(),
                overview.officialSite(),
                overview.marketCapitalization(),
                overview.peRatio(),
                overview.eps(),
                overview.fiftyTwoWeekHigh(),
                overview.fiftyTwoWeekLow()
        );
    }
}
