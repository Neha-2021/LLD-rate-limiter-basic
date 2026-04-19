package org.example.strategies;

public interface RateLimitingStrategy {
    boolean allowRequest(String key);
}