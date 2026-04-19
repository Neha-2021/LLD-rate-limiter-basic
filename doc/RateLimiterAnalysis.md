## Rate Limiter

### Requirements
1. System should allow/reject request based on rate limiting policy
2. System should support per-user limiting
3. Limits should be configurable
4. System should handle concurrent requests correctly
5. Design should allow plugging in different rate-limiting algorithms
6. Limits based on different types of user, like regular or premium (Optional)
7. System should handle pluggable storage like in-memory(default), Redis (Optional)
8. Expose metadata like remaining tokens, time after which retries are possible (Optional)

### Core Entities
1. RateLimiter
2. RateLimitingStrategy
3. RateLimiterState
4. RateLimiterStore
5. RateLimiterConfig
6. ConfigProvider