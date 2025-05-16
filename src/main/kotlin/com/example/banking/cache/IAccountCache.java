package com.example.banking.cache;

import com.example.banking.domain.Account;

public interface IAccountCache {
    Account getCache(String id);
    void putCache(String id, Account account);
}
