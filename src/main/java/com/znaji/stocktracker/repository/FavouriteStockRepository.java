package com.znaji.stocktracker.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteStockRepository extends org.springframework.data.jpa.repository.JpaRepository<com.znaji.stocktracker.model.FavouriteStock, Long> {
     boolean existsBySymbol(String symbol);
}
