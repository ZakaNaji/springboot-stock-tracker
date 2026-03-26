package com.znaji.stocktracker.client.fmp;

import com.znaji.stocktracker.client.StockClientProperties;
import com.znaji.stocktracker.client.StockMarketClient;
import com.znaji.stocktracker.client.fmp.dto.FmpQuoteResponse;
import com.znaji.stocktracker.client.fmp.mapper.FmpQuoteMapper;
import com.znaji.stocktracker.exception.StockNotFoundException;
import com.znaji.stocktracker.exception.StockProviderException;
import com.znaji.stocktracker.model.StockHistory;
import com.znaji.stocktracker.model.StockOverview;
import com.znaji.stocktracker.model.StockQuote;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@ConditionalOnProperty(name = "stock.provider", havingValue = "fmp")
@Log4j2
public class FmpMarketClient implements StockMarketClient {
    private final RestClient restClient;
    private final FmpQuoteMapper quoteMapper;
    private final StockClientProperties.ProviderProperty properties;

    public FmpMarketClient(RestClient restClient, FmpQuoteMapper quoteMapper, StockClientProperties config) {
        this.restClient = restClient;
        this.quoteMapper = quoteMapper;
        this.properties = config.getProviders().get("fmp");
    }

    @Override
    public StockQuote getStockQuote(String symbol) {
        try {
            log.info("[FMP] Fetching stock quote for symbol: {}", symbol);
            var response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/quote")
                            .queryParam("symbol", symbol)
                            .queryParam("apikey", properties.getApiKey())
                            .build())
                    .retrieve()
                    .body(FmpQuoteResponse[].class);

            return quoteMapper.toStockQuote(response[0]);
        } catch (StockNotFoundException | StockProviderException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new StockProviderException("Failed to fetch stock quote for symbol: " + symbol, ex);
        }

    }

    @Override
    public StockOverview getStockOverview(String symbol) {
        return null;
    }

    @Override
    public StockHistory getStockHistory(String symbol, int days) {
        return null;
    }
}
