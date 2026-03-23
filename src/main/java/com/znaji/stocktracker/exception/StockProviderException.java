package com.znaji.stocktracker.exception;

public class StockProviderException extends RuntimeException {
    public StockProviderException(String s) {
        super(s);
    }

    public StockProviderException(String s, Throwable cause) {
        super(s, cause);
    }
}
