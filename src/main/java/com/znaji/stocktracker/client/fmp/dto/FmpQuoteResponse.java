package com.znaji.stocktracker.client.fmp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FmpQuoteResponse {

    private String symbol;
    private String name;
    private Double price;
    private Double changePercentage;
    private Double change;
    private Long volume;
    private Double dayLow;
    private Double dayHigh;
    private Double yearHigh;
    private Double yearLow;
    private Double marketCap;
    private Double priceAvg50;
    private Double priceAvg200;
    private String exchange;
    private Double open;
    private Double previousClose;
    private Long timestamp;
}