package com.example.banking.cache;

import com.example.banking.domain.Account;

public class InMemoryCache {
    private final Account account;
    private final long cachedAt;
    private final long ttl;

    public InMemoryCache(Account account, long cachedAt, long ttl) {
        this.account = account;
        this.cachedAt = cachedAt;
        this.ttl = ttl;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - cachedAt > ttl;
    }

    public Account getAccount() {
        return account;
    }
}
