package com.znaji.stocktracker.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "stock")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class StockClientProperties {

    private String provider;
    private Map<String, ProviderProperty> providers = new HashMap<>();

    @AllArgsConstructor @NoArgsConstructor
    @Getter @Setter
    public static class ProviderProperty {
        private String apiKey;
        private String baseUrl;

    }
}
