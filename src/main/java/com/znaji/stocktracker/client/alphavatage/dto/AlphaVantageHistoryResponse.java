package com.znaji.stocktracker.client.alphavatage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlphaVantageHistoryResponse {

    @JsonProperty("Meta Data")
    private MetaData metaData;

    @JsonProperty("Time Series (Daily)")
    private Map<String, DailyPrice> timeSeriesDaily;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class MetaData {

        @JsonProperty("1. Information")
        private String information;

        @JsonProperty("2. Symbol")
        private String symbol;

        @JsonProperty("3. Last Refreshed")
        private String lastRefreshed;

        @JsonProperty("4. Output Size")
        private String outputSize;

        @JsonProperty("5. Time Zone")
        private String timeZone;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DailyPrice {

        @JsonProperty("1. open")
        private String open;

        @JsonProperty("2. high")
        private String high;

        @JsonProperty("3. low")
        private String low;

        @JsonProperty("4. close")
        private String close;

        @JsonProperty("5. volume")
        private String volume;
    }
}
