package com.znaji.stocktracker.utils;

import com.znaji.stocktracker.exception.StockProviderException;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Helpers {
    private Helpers() {
        // Private constructor to prevent instantiation
    }

    public static BigDecimal parseBigDecimal(String value) {
        if (isBlank(value) || "None".equalsIgnoreCase(value)) {
            return null;
        }
        try {
            return new BigDecimal(value.trim());
        } catch (NumberFormatException ex) {
            throw new StockProviderException("Invalid numeric value from Alpha Vantage: " + value);
        }
    }

    public static BigDecimal toBigDecimal(Double value) {
        if (value == null) {
            return null;
        }
        try {
            return BigDecimal.valueOf(value);
        } catch (Exception ex) {
            throw new StockProviderException("Invalid numeric value from FMP: " + value, ex);
        }
    }

    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public static LocalDateTime toLocalDateTime(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        try {
            return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC);
        } catch (Exception ex) {
            throw new StockProviderException("Invalid timestamp value from FMP: " + timestamp, ex);
        }
    }
}
