package com.znaji.stocktracker.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.znaji.stocktracker.utils.Constants.*;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        var quoteCache = new CaffeineCache(STOCK_QUOTE_CACHE,
                Caffeine.newBuilder()
                        .expireAfterWrite(10, java.util.concurrent.TimeUnit.MINUTES)
                        .maximumSize(1000)
                        .build());
        var overviewCache = new CaffeineCache(STOCK_OVERVIEW_CACHE,
                Caffeine.newBuilder()
                        .expireAfterWrite(60, java.util.concurrent.TimeUnit.MINUTES)
                        .maximumSize(1000)
                        .build());

        var historyCache = new CaffeineCache(STOCK_HISTORY_CACHE,
                Caffeine.newBuilder()
                        .expireAfterWrite(60, java.util.concurrent.TimeUnit.MINUTES)
                        .maximumSize(1000)
                        .build());

        var cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(
                quoteCache,
                overviewCache,
                historyCache
        ));
        return cacheManager;
    }
}
