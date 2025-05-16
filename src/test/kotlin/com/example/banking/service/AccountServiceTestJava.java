package com.example.banking.service;

import com.example.banking.domain.Account;
import com.example.banking.lock.PessimisticLockManager;
import com.example.banking.validator.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountServiceTestJava {
    private AccountService accountService;
    private EmailService mockEmailService;
    AccountLockService accountLockService;
    PessimisticLockManager lockManager;

    @BeforeEach
    public void setUp(){
        // setUp
        // given
        mockEmailService = Mockito.mock(EmailService.class);
        Validator validator = new Validator();
//        PessimisticLockManager pessimisticLockManager = new PessimisticLockManager();
        lockManager = Mockito.mock(PessimisticLockManager.class);
        accountLockService = new AccountLockService(lockManager);
        accountService = new AccountService(mockEmailService, validator, accountLockService);
    }

    @Test
    public void 계좌생성(){

    }

    @Test
    @DisplayName("이메일 전송 호출")
    void registerUser_이메일전송이_호출되어야한다() {
        // given


        // when
        accountService.createAccount("1", "gunha");

        // then
        Mockito.verify(mockEmailService).sendWelcomeEmail("gunha"); // 호출 여부 검증
    }

    @Test
    void deposit_shouldIncreaseBalance() {

        accountService.createAccount("1", "건하");
        accountService.deposit("1", 100);

        Account account = accountService.getAccount("1");
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    void 멀티스레드_환경에서의_동시성_안전성_검증() throws InterruptedException {

        accountService.createAccount("1", "user");

        int threadCount = 100;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                accountService.deposit("1", 10);
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        Assertions.assertEquals(1000, accountService.getAccount("1").getBalance());
    }

    @Test
    void 락_매니저가_실제로_호출되는지_구조적으로_검증() {
        // given
        accountService.createAccount("1", "user");

        // when
        accountService.deposit("1", 100);

        // then
        Mockito.verify(lockManager, Mockito.times(1))
                .executeWithLock(Mockito.eq("1"), Mockito.any(Runnable.class));
    }
}
