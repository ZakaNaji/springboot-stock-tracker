package com.znaji.stocktracker.exception;

public class FavouriteStockExistsException extends RuntimeException {
    public FavouriteStockExistsException(String symbol) {
        super("Favourite stock with symbol '" + symbol + "' already exists.");
    }
}
