package org.example.ratelimiter;

public class TokenBucket {
    private final int capacity;
    private final double refillRate;
    private double token;
    private long lastRefillTimeInSec;

    public TokenBucket(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.token = capacity;
        this.lastRefillTimeInSec = System.currentTimeMillis()/1000;
    }

    private void refill() {
        long currentTimeInSec = System.currentTimeMillis()/1000;
        long timeElapsedInSec = currentTimeInSec - lastRefillTimeInSec;
        double refillTokens = refillRate*timeElapsedInSec;

        token = Math.min(refillTokens+token, capacity);
        lastRefillTimeInSec = System.currentTimeMillis()/1000;
    }

    public boolean consumeToken() {
        refill();

        if(token >= 1) {
            token--;
            return true;
        }
        return false;
    }
}
