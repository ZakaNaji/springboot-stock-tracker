package com.znaji.stocktracker.config;

import com.znaji.stocktracker.client.alphavatage.AlphaVantageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(AlphaVantageProperties.class)
public class HttpClientConfig {

    @Bean
    public RestClient alphaVantageRestClient(AlphaVantageProperties properties) {
        return RestClient.builder()
                .baseUrl(properties.getBaseUrl())
                .build();
    }
}
