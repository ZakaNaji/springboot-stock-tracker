package com.znaji.stocktracker.dto.response;

import java.time.Instant;

public record FavoriteStockResponse(
        Long id,
        String symbol,
        Instant createdAt
) {
}
