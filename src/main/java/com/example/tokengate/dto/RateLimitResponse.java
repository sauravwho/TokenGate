package com.example.tokengate.dto;

public class RateLimitResponse {
    private final boolean allowed;
    private final long remainingTokens;

    public RateLimitResponse(boolean allowed, long remainingTokens) {
        this.allowed = allowed;
        this.remainingTokens = remainingTokens;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public long getRemainingTokens() {
        return remainingTokens;
    }
}
