package org.example;

import org.example.config.ConfigProvider;
import org.example.config.InMemoryConfigProvider;
import org.example.config.RateLimiterConfig;
import org.example.entities.InMemoryRateLimiterStore;
import org.example.entities.RateLimiterStore;
import org.example.ratelimiter.RateLimiter;
import org.example.strategies.RateLimitingStrategy;
import org.example.strategies.TokenBucketStrategy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterConfig config = new RateLimiterConfig();

        config.setCapacity(5);
        config.setRefillRate(3);

        ConfigProvider configProvider = new InMemoryConfigProvider(config);
        RateLimiterStore store = new InMemoryRateLimiterStore();
        RateLimitingStrategy strategy = new TokenBucketStrategy(store,configProvider);
        RateLimiter rateLimiter = new RateLimiter(strategy);

        String userId = "User-001";

        for(int i=1; i<=10; i++) {
            System.out.println("Request " + i + " at T=0 succeeded: "+ rateLimiter.allowRequest(userId));
        }

        System.out.println("Request 0 at T=1, succeeded: " + rateLimiter.allowRequest(userId));

        long timeToSleep = 3000;
        Thread.sleep(timeToSleep);

        // Should allow 1 request
        System.out.println("After " + timeToSleep/1000 + "s " + rateLimiter.allowRequest(userId));
    }
}