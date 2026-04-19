package org.example.entities;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRateLimiterStore implements RateLimiterStore{

    ConcurrentHashMap<String, RateLimiterState> userToLimitMapping = new ConcurrentHashMap<>();
    @Override
    public RateLimiterState get(String userId) {
        return userToLimitMapping.get(userId);
    }

    @Override
    public void put(String userId, RateLimiterState state) {
        userToLimitMapping.put(userId, state);
    }
}
