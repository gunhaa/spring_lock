package com.example.banking.cache;

import com.example.banking.domain.Account;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryAccountCache implements IAccountCache {

    private final Map<String, InMemoryCache> cache = new ConcurrentHashMap<>();
    private final CacheSetting cacheSetting;

    public InMemoryAccountCache(CacheSetting cacheSetting) {
        this.cacheSetting = cacheSetting;
    }

    @Override
    public Account getCache(String id) {
        InMemoryCache cached = cache.get(id);
        if (cached != null && !cached.isExpired()) {
            System.out.println("Cache HIT (InMemory): " + id);
            return cached.getAccount();
        }
        System.out.println("Cache MISS (InMemory): " + id);
        return null;
    }

    @Override
    public void putCache(String id, Account account) {
        System.out.println("this ttl: " + this.cacheSetting.getTtl());
        cache.put(id, new InMemoryCache(account, System.currentTimeMillis(), cacheSetting.getTtl()));
    }

    @Scheduled(fixedRate = 10_000)
    public void cleanExpiredCache() {
        cache.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }
}
