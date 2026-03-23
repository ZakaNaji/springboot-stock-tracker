package com.znaji.stocktracker.client.alphavatage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlphaVantageOverviewResponse {

    @JsonProperty("Symbol")
    private String symbol;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Exchange")
    private String exchange;

    @JsonProperty("Currency")
    private String currency;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Sector")
    private String sector;

    @JsonProperty("Industry")
    private String industry;

    @JsonProperty("OfficialSite")
    private String officialSite;

    @JsonProperty("MarketCapitalization")
    private String marketCapitalization;

    @JsonProperty("PERatio")
    private String peRatio;

    @JsonProperty("EPS")
    private String eps;

    @JsonProperty("52WeekHigh")
    private String fiftyTwoWeekHigh;

    @JsonProperty("52WeekLow")
    private String fiftyTwoWeekLow;
}
