package com.example.banking.service;

import com.example.banking.lock.PessimisticLockManager;
import org.springframework.stereotype.Service;

@Service
public class AccountLockService {

    // 락 처리 방법이 변한다면, 해당 클래스의 의존성을 교체해 전략을 수정 할 수 있다
    private final PessimisticLockManager lockManager;

    public AccountLockService(PessimisticLockManager lockManager) {
        this.lockManager = lockManager;
    }

    public void withAccountLock(String accountId, Runnable task) {
        lockManager.executeWithLock(accountId, task);
    }
}