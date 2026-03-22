package com.znaji.stocktracker.controller;

import com.znaji.stocktracker.dto.FavoriteStockResponse;
import com.znaji.stocktracker.dto.FavouriteStockRequest;
import com.znaji.stocktracker.model.FavouriteStock;
import com.znaji.stocktracker.service.FavouriteStockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteStockController {

    private final FavouriteStockService favouriteStoService;

    public FavouriteStockController(FavouriteStockService favouriteStoService) {
        this.favouriteStoService = favouriteStoService;
    }

    @GetMapping
    public List<FavoriteStockResponse> getFavouriteStocks() {
        List<FavouriteStock> stocks = favouriteStoService.getFavouriteStocks();
        return stocks.stream()
                .map(this::mapToResponse)
                .toList();
    }


    @PostMapping("")
    public FavoriteStockResponse addFavouriteStock(@RequestBody FavouriteStockRequest request) {
        String symbol = request.symbol();
        FavouriteStock response = favouriteStoService.addFavouriteStock(symbol);
        return mapToResponse(response);
    }

    private FavoriteStockResponse mapToResponse(FavouriteStock stock) {
        return new FavoriteStockResponse(stock.getId(), stock.getSymbol(), stock.getCreatedAt());
    }
}
