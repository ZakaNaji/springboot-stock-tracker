package com.znaji.stocktracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class StockQuoteResponse {

    private String symbol;
    private String companyName;
    private BigDecimal price;
    private BigDecimal change;
    private BigDecimal changePercent;
    private BigDecimal dayLow;
    private BigDecimal dayHigh;
    private BigDecimal open;
    private BigDecimal previousClose;
    private Long volume;
    private LocalDateTime quoteTime;
}
