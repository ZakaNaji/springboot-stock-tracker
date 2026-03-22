package com.znaji.stocktracker.service;

import com.znaji.stocktracker.model.FavouriteStock;

import java.util.List;

public interface FavouriteStockService {
    FavouriteStock addFavouriteStock(String symbol);

    List<FavouriteStock> getFavouriteStocks();
}
