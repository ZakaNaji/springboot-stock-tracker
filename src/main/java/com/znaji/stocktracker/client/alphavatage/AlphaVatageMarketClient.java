package com.znaji.stocktracker.client.alphavatage;

import com.znaji.stocktracker.client.StockMarketClient;
import com.znaji.stocktracker.client.alphavatage.dto.AlphaVantageQuoteResponse;
import com.znaji.stocktracker.client.alphavatage.mapper.AlphaVantageQuoteMapper;
import com.znaji.stocktracker.exception.StockNotFoundException;
import com.znaji.stocktracker.model.StockQuote;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AlphaVatageMarketClient implements StockMarketClient {

    private final AlphaVantageProperties properties;
    private final RestClient restClient;
    private final AlphaVantageQuoteMapper mapper;

    public AlphaVatageMarketClient(AlphaVantageProperties properties, RestClient restClient, AlphaVantageQuoteMapper mapper) {
        this.properties = properties;
        this.restClient = restClient;
        this.mapper = mapper;
    }

    @Override
    public StockQuote getStockQuote(String symbol) {
        AlphaVantageQuoteResponse alphaVantageQuote = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("function", "GLOBAL_QUOTE")
                        .queryParam("symbol", symbol)
                        .queryParam("apikey", properties.getApiKey())
                        .build())
                .retrieve()
                .body(AlphaVantageQuoteResponse.class);

        if (alphaVantageQuote == null || alphaVantageQuote.getGlobalQuote() == null) {
            throw new StockNotFoundException(symbol);
        }
        return mapper.toStockQuote(alphaVantageQuote);
    }
}
