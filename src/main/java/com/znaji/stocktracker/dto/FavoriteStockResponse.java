package com.znaji.stocktracker.dto;

import java.time.Instant;

public record FavoriteStockResponse(
        Long id,
        String symbol,
        Instant createdAt
) {
}
