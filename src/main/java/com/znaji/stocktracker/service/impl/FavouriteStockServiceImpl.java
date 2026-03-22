package com.znaji.stocktracker.service.impl;

import com.znaji.stocktracker.exception.FavouriteStockExistsException;
import com.znaji.stocktracker.exception.InvalidStockRequestException;
import com.znaji.stocktracker.model.FavouriteStock;
import com.znaji.stocktracker.repository.FavouriteStockRepository;
import com.znaji.stocktracker.service.FavouriteStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavouriteStockServiceImpl implements FavouriteStockService {

    private final FavouriteStockRepository favouriteStockRepository;

    public FavouriteStockServiceImpl(FavouriteStockRepository favouriteStockRepository) {
        this.favouriteStockRepository = favouriteStockRepository;
    }

    @Override
    @Transactional
    public FavouriteStock addFavouriteStock(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new InvalidStockRequestException("Stock symbol cannot be null or empty");
        }

        var normalizedSymbol = symbol.trim().toUpperCase();
        if (favouriteStockRepository.existsBySymbol(normalizedSymbol)) {
            throw new FavouriteStockExistsException(normalizedSymbol);
        }
        var newStock = new FavouriteStock();
        newStock.setSymbol(normalizedSymbol);
        return favouriteStockRepository.save(newStock);
    }

    @Override
    public List<FavouriteStock> getFavouriteStocks() {
        return favouriteStockRepository.findAll();
    }
}
