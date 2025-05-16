package com.example.banking.service

import com.example.banking.domain.Account
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AccountServiceTest {

    private lateinit var accountService: AccountService

    @BeforeEach
    fun setUp() {
//        accountService = AccountService()
    }

    @Test
    fun `계좌를 생성할 수 있다`() {
        // TODO: 계좌 생성 후 반환 값과 상태 검증
    }

    @Test
    fun `입금 시 잔액이 증가한다`() {
        // TODO: 계좌 생성 후 입금하고 잔액 확인
    }

    @Test
    fun `출금 시 잔액이 감소한다`() {
        // TODO: 입금 후 출금하고 잔액 확인
    }

    @Test
    fun `잔액보다 많은 금액 출금 시 예외가 발생한다`() {
        // TODO: 예외 발생 여부 테스트
    }

    @Test
    fun `존재하지 않는 계좌 접근 시 예외가 발생한다`() {
        // TODO: 존재하지 않는 ID로 접근 시 IllegalArgumentException 검증
    }
}