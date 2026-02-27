package org.example.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final Map<String, TokenBucket> buckets = new ConcurrentHashMap<>();

    public boolean allowRequest(String userId) {
        buckets.putIfAbsent(userId, new TokenBucket(5, 0.083));
        return buckets.get(userId).consumeToken();
    }
}
