package com.znaji.stocktracker.client.alphavatage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stock.providers.alphavantage")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AlphaVantageProperties {

    private String apiKey;
    private String baseUrl;

}
