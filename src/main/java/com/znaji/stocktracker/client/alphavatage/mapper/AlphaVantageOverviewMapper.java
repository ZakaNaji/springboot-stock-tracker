package com.znaji.stocktracker.client.alphavatage.mapper;

import com.znaji.stocktracker.client.alphavatage.dto.AlphaVantageOverviewResponse;
import com.znaji.stocktracker.exception.StockNotFoundException;
import com.znaji.stocktracker.exception.StockProviderException;
import com.znaji.stocktracker.model.StockOverview;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.znaji.stocktracker.utils.Helpers.isBlank;
import static com.znaji.stocktracker.utils.Helpers.parseBigDecimal;

@Component
public class AlphaVantageOverviewMapper {

    public StockOverview toStockOverview(AlphaVantageOverviewResponse response) {
        if (response == null || isBlank(response.getSymbol())) {
            throw new StockNotFoundException("No stock overview found");
        }

        return new StockOverview(
                response.getSymbol(),
                response.getName(),
                response.getDescription(),
                response.getExchange(),
                response.getCurrency(),
                response.getCountry(),
                response.getSector(),
                response.getIndustry(),
                response.getOfficialSite(),
                parseBigDecimal(response.getMarketCapitalization()),
                parseBigDecimal(response.getPeRatio()),
                parseBigDecimal(response.getEps()),
                parseBigDecimal(response.getFiftyTwoWeekHigh()),
                parseBigDecimal(response.getFiftyTwoWeekLow())
        );
    }


}