package com.znaji.stocktracker.client.alphavatage;

import com.znaji.stocktracker.client.StockMarketClient;
import com.znaji.stocktracker.client.alphavatage.dto.AlphaVantageHistoryResponse;
import com.znaji.stocktracker.client.alphavatage.dto.AlphaVantageOverviewResponse;
import com.znaji.stocktracker.client.alphavatage.dto.AlphaVantageQuoteResponse;
import com.znaji.stocktracker.client.alphavatage.mapper.AlphaVantageHistoryMapper;
import com.znaji.stocktracker.client.alphavatage.mapper.AlphaVantageQuoteMapper;
import com.znaji.stocktracker.exception.StockNotFoundException;
import com.znaji.stocktracker.exception.StockProviderException;
import com.znaji.stocktracker.model.StockHistory;
import com.znaji.stocktracker.model.StockOverview;
import com.znaji.stocktracker.model.StockQuote;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Log4j2
public class AlphaVatageMarketClient implements StockMarketClient {

    private final AlphaVantageProperties properties;
    private final RestClient restClient;
    private final AlphaVantageQuoteMapper quoteMapper;
    private final AlphaVantageHistoryMapper historyMapper;

    public AlphaVatageMarketClient(AlphaVantageProperties properties, RestClient restClient, AlphaVantageQuoteMapper quoteMapper, AlphaVantageHistoryMapper historyMapper) {
        this.properties = properties;
        this.restClient = restClient;
        this.quoteMapper = quoteMapper;
        this.historyMapper = historyMapper;
    }

    @Override
    public StockQuote getStockQuote(String symbol) {
        try {
            log.info("Fetching stock quote for symbol: {}", symbol);
            AlphaVantageQuoteResponse alphaVantageQuote = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("function", "GLOBAL_QUOTE")
                            .queryParam("symbol", symbol)
                            .queryParam("apikey", properties.getApiKey())
                            .build())
                    .retrieve()
                    .body(AlphaVantageQuoteResponse.class);

            return quoteMapper.toStockQuote(alphaVantageQuote);
        } catch (StockNotFoundException | StockProviderException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new StockProviderException("Failed to fetch stock quote for symbol: " + symbol, ex);
        }
    }

    @Override
    public StockOverview getStockOverview(String symbol) {
        try {
            log.info("Fetching stock overview for symbol: {}", symbol);
            var alphaVantageResponse = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("function", "OVERVIEW")
                            .queryParam("symbol", symbol)
                            .queryParam("apikey", properties.getApiKey())
                            .build())
                    .retrieve()
                    .body(AlphaVantageOverviewResponse.class);
            return quoteMapper.toStockOverview(alphaVantageResponse);

        } catch (StockNotFoundException | StockProviderException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new StockProviderException("Failed to fetch stock overview for symbol: " + symbol, ex);
        }
    }

    @Override
    public StockHistory getStockHistory(String symbol, int days) {
        try {
            log.info("Fetching stock history for symbol {} with days {}", symbol, days);            AlphaVantageHistoryResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("function", "TIME_SERIES_DAILY")
                            .queryParam("symbol", symbol)
                            .queryParam("apikey", properties.getApiKey())
                            .build())
                    .retrieve()
                    .body(AlphaVantageHistoryResponse.class);
            return historyMapper.toStockHistory(response, days);

        } catch (StockNotFoundException | StockProviderException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new StockProviderException("Failed to fetch stock history for symbol: " + symbol, ex);
        }

    }
}
