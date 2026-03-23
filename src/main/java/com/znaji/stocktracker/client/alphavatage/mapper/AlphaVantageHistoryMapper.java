package com.znaji.stocktracker.client.alphavatage.mapper;

import com.znaji.stocktracker.client.alphavatage.dto.AlphaVantageHistoryResponse;
import com.znaji.stocktracker.exception.StockNotFoundException;
import com.znaji.stocktracker.exception.StockProviderException;
import com.znaji.stocktracker.model.HistoricalPrice;
import com.znaji.stocktracker.model.StockHistory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.znaji.stocktracker.utils.Helpers.isBlank;
import static com.znaji.stocktracker.utils.Helpers.parseBigDecimal;
import static java.lang.Long.parseLong;

@Component
public class AlphaVantageHistoryMapper {

    public StockHistory toStockHistory(AlphaVantageHistoryResponse response, int days) {
        if (response == null || response.getMetaData() == null) {
            throw new StockNotFoundException("No stock history found");
        }

        String symbol = response.getMetaData().getSymbol();
        Map<String, AlphaVantageHistoryResponse.DailyPrice> rawSeries = response.getTimeSeriesDaily();

        if (isBlank(symbol) || rawSeries == null || rawSeries.isEmpty()) {
            throw new StockNotFoundException("No stock history found");
        }

        List<HistoricalPrice> prices = rawSeries.entrySet().stream()
                .map(this::toHistoricalPrice)
                .sorted(Comparator.comparing(HistoricalPrice::date)) // oldest first
                .skip(Math.max(0, rawSeries.size() - days)) // keep latest N after sorting
                .toList();

        return new StockHistory(symbol, days, prices);
    }

    private HistoricalPrice toHistoricalPrice(Map.Entry<String, AlphaVantageHistoryResponse.DailyPrice> entry) {
        String rawDate = entry.getKey();
        AlphaVantageHistoryResponse.DailyPrice daily = entry.getValue();

        try {
            return new HistoricalPrice(
                    LocalDate.parse(rawDate),
                    parseBigDecimal(daily.getOpen()),
                    parseBigDecimal(daily.getHigh()),
                    parseBigDecimal(daily.getLow()),
                    parseBigDecimal(daily.getClose()),
                    parseLong(daily.getVolume())
            );
        } catch (Exception ex) {
            throw new StockProviderException("Invalid historical price entry for date: " + rawDate, ex);
        }
    }
}
