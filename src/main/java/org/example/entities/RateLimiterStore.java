package org.example.entities;

public interface RateLimiterStore {
    RateLimiterState get(String userId);
    void put(String userId, RateLimiterState state);
}
