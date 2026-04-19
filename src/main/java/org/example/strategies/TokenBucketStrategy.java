package org.example.strategies;

import org.example.config.ConfigProvider;
import org.example.config.RateLimiterConfig;
import org.example.entities.RateLimiterState;
import org.example.entities.RateLimiterStore;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketStrategy implements RateLimitingStrategy {
    private final RateLimiterStore rateLimiterStore;
    private final ConfigProvider configProvider;
    private final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

    public TokenBucketStrategy(RateLimiterStore rateLimiterStore, ConfigProvider configProvider) {
        this.rateLimiterStore = rateLimiterStore;
        this.configProvider = configProvider;
    }

    @Override
    public boolean allowRequest(String userId) {
        Object lock = locks.computeIfAbsent(userId, k -> new Object());
        synchronized (lock) {
            RateLimiterState state = rateLimiterStore.get(userId);
            RateLimiterConfig config = configProvider.getConfig(userId);

            if (state == null) {
                state = new RateLimiterState(config.getCapacity(), System.currentTimeMillis());
            }

            long currentTimeInMillis = System.currentTimeMillis();
            long timeElapsedInMillis = currentTimeInMillis - state.lastRefilledTimeStampInMillis;
            int tokensToRefill = (int) (config.getRefillRate()/1000 * timeElapsedInMillis);

            if (tokensToRefill > 0) {
                state.tokens = Math.min(tokensToRefill + state.tokens, config.getCapacity());
                state.lastRefilledTimeStampInMillis = currentTimeInMillis;
            }

            boolean allowed = false;
            if (state.tokens >= 1) {
                state.tokens--;
                allowed = true;
            }
            rateLimiterStore.put(userId, state);
            return allowed;
        }
    }
}