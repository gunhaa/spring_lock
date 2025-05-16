package com.example.banking.service;

import com.example.banking.cache.CacheSetting;
import com.example.banking.cache.InMemoryAccountCache;
import com.example.banking.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {
    private InMemoryAccountCache cache;

    @BeforeEach
    void setUp() {
        CacheSetting settings = new CacheSetting();
        settings.setTtl(1000); // TTL 1초 설정
        cache = new InMemoryAccountCache(settings);
    }

    @Test
    void 캐시에_저장_후_즉시_조회시_성공() {
        Account account = new Account("user1", "건하", 1000L);
        cache.putCache("user1", account);

        Account result = cache.getCache("user1");

        assertNotNull(result);
        assertEquals("건하", result.getOwner());
    }

    @Test
    void TTL_이_지나면_MISS_되어야_한다() throws InterruptedException {
        Account account = new Account("user1", "건하", 1000L);
        cache.putCache("user1", account);

        Thread.sleep(1100); // TTL 넘기기

        Account result = cache.getCache("user1");
        assertNull(result);
    }

    @Test
    void cleanExpiredCache_는_만료된_항목을_제거해야_한다() throws InterruptedException {
        Account account = new Account("user1", "건하", 1000L);
        cache.putCache("user1", account);

        Thread.sleep(1100); // TTL 넘기기
        cache.cleanExpiredCache();

        Account result = cache.getCache("user1");
        assertNull(result);
    }
}
