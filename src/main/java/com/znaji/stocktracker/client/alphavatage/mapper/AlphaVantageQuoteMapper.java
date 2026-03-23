package com.znaji.stocktracker.client.alphavatage.mapper;

import com.znaji.stocktracker.client.alphavatage.dto.AlphaVantageOverviewResponse;
import com.znaji.stocktracker.client.alphavatage.dto.AlphaVantageQuoteResponse;
import com.znaji.stocktracker.exception.StockProviderException;
import com.znaji.stocktracker.model.StockOverview;
import com.znaji.stocktracker.model.StockQuote;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class AlphaVantageQuoteMapper {

    public StockQuote toStockQuote(AlphaVantageQuoteResponse response) {
        if (response == null || response.getGlobalQuote() == null) {
            throw new StockProviderException("Alpha Vantage returned an empty response");
        }

        AlphaVantageQuoteResponse.GlobalQuote quote = response.getGlobalQuote();

        if (isBlank(quote.getSymbol())) {
            throw new StockProviderException("Alpha Vantage did not return a valid quote");
        }

        return new StockQuote(
                quote.getSymbol(),
                null, // companyName is not available in GLOBAL_QUOTE
                parseBigDecimal(quote.getPrice()),
                parseBigDecimal(quote.getChange()),
                parsePercent(quote.getChangePercent()),
                parseBigDecimal(quote.getLow()),
                parseBigDecimal(quote.getHigh()),
                parseBigDecimal(quote.getOpen()),
                parseBigDecimal(quote.getPreviousClose()),
                parseLong(quote.getVolume()),
                parseQuoteTime(quote.getLatestTradingDay())
        );
    }

    private BigDecimal parseBigDecimal(String value) {
        if (isBlank(value) || "None".equalsIgnoreCase(value)) {
            return null;
        }
        try {
            return new BigDecimal(value.trim());
        } catch (NumberFormatException ex) {
            throw new StockProviderException("Invalid decimal value from Alpha Vantage: " + value);
        }
    }

    private BigDecimal parsePercent(String value) {
        if (isBlank(value)) {
            return null;
        }
        try {
            return new BigDecimal(value.replace("%", "").trim());
        } catch (NumberFormatException ex) {
            throw new StockProviderException("Invalid percent value from Alpha Vantage: " + value);
        }
    }

    private Long parseLong(String value) {
        if (isBlank(value)) {
            return null;
        }
        try {
            return Long.valueOf(value.trim());
        } catch (NumberFormatException ex) {
            throw new StockProviderException("Invalid long value from Alpha Vantage: " + value);
        }
    }

    private LocalDateTime parseQuoteTime(String value) {
        if (isBlank(value)) {
            return null;
        }
        try {
            return LocalDate.parse(value.trim()).atStartOfDay();
        } catch (Exception ex) {
            throw new StockProviderException("Invalid date value from Alpha Vantage: " + value);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public StockOverview toStockOverview(AlphaVantageOverviewResponse response) {
        if (response == null || isBlank(response.getSymbol())) {
            throw new StockProviderException("Alpha Vantage returned an empty overview response");
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