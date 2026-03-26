package com.znaji.stocktracker.config;

import com.znaji.stocktracker.client.StockClientProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(StockClientProperties.class)
public class HttpClientConfig {

    @Bean
    @ConditionalOnProperty(name = "stock.provider", havingValue = "alphavantage")
    public RestClient alphaVantageRestClient(StockClientProperties properties) {
        return RestClient.builder()
                .baseUrl(properties.getProviders().get("alphavantage").getBaseUrl())
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "stock.provider", havingValue = "fmp")
    public RestClient fmpRestClient(StockClientProperties properties) {
        return RestClient.builder()
                .baseUrl(properties.getProviders().get("fmp").getBaseUrl())
                .build();
    }
}
