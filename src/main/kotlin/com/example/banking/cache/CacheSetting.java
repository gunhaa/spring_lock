package com.example.banking.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "account.cache")
public class CacheSetting {
    // application.yml에 값이 없다면 ttl이 5000이 들어가게됨
    private long ttl = 5000;

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}