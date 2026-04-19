package org.example.entities;

public class RateLimiterState {
    public int tokens;
    public long lastRefilledTimeStampInMillis;

    public RateLimiterState(int tokens, long lastRefilledTimeStampInMillis) {
        this.tokens = tokens;
        this.lastRefilledTimeStampInMillis = lastRefilledTimeStampInMillis;
    }
}
