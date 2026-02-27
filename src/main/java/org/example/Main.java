package org.example;

import org.example.ratelimiter.RateLimiter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter();
        String userId = "User";

        for(int i=0; i<5; i++) {
            System.out.println("Request at " + i + " and T=0 succeeded: "+ rateLimiter.allowRequest(userId));
        }

        System.out.println("Request at T=1, succeeded: " + rateLimiter.allowRequest(userId));

        Thread.sleep(30000);

        // Should now allow 1 request
        System.out.println("After 30s: " + rateLimiter.allowRequest(userId));
    }
}