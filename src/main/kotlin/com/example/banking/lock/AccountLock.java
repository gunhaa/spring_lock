package com.example.banking.lock;

public class AccountLock {
    private final String accountId;

    public AccountLock(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }
}
