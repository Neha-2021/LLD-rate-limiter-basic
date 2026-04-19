package org.example.config;

public interface ConfigProvider {
    RateLimiterConfig getConfig(String userId);
}
