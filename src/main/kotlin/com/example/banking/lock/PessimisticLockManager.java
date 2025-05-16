package com.example.banking.lock;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PessimisticLockManager {

    // 각 리소스(accountId 등)에 대한 고유 락 객체를 저장하는 맵
    private final Map<String, Object> locks = new ConcurrentHashMap<>();

    /**
     * 특정 key(accountId 등)에 대해 동기화된 코드 블록을 실행합니다.
     *
     * @param accountId   리소스를 구분하기 위한 고유 키 (예: 계좌 ID)
     * @param task  동기화되어 실행될 비즈니스 로직
     */
    public void executeWithLock(String accountId, Runnable task) {
        synchronized (getLockForKey(accountId)) {
            task.run();
        }
    }

    /**
     * 주어진 키에 대해 고유한 락 객체를 반환합니다.
     * 기존에 없으면 새 Object()를 생성해서 저장합니다.
     *
     * @param accountId 동기화를 위한 기준 키
     * @return key에 해당하는 락 객체
     */
    private Object getLockForKey(String accountId) {
        // computeIfAbsent는 스레드 안전하게 동작
        // key에 해당하는 락 객체가 없을 경우 새 Object 생성
        // 있을 경우
        return locks.computeIfAbsent(accountId, k -> new AccountLock(accountId));
    }
}
