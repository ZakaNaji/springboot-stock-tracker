package com.znaji.stocktracker.utils;

import com.znaji.stocktracker.exception.StockProviderException;

import java.math.BigDecimal;

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

    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
