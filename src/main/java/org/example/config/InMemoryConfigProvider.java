package org.example.config;

public class InMemoryConfigProvider implements ConfigProvider {
    private final RateLimiterConfig config;

    public InMemoryConfigProvider(RateLimiterConfig config) {
        this.config = config;
    }

    @Override
    public RateLimiterConfig getConfig(String userId) {
        return config;
    }
}
