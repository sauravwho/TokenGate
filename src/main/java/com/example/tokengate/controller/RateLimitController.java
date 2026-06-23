package com.example.tokengate.controller;

import com.example.tokengate.dto.RateLimitResponse;
import com.example.tokengate.service.TokenBucketRateLimiter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/ratelimit")
public class RateLimitController {
    private final TokenBucketRateLimiter rateLimiter;

    public RateLimitController(TokenBucketRateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @PostMapping("/check")
    public RateLimitResponse check(@RequestParam String clientId) {
        return rateLimiter.tryConsume(clientId);
    }

}
