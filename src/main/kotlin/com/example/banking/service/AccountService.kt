package com.example.banking.service

import com.example.banking.customExceptionHandler.AccountNotFoundException
import com.example.banking.domain.Account
import com.example.banking.lock.PessimisticLockManager
import com.example.banking.validator.Validator
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class AccountService(
    private val emailService: EmailService,
    private val validator: Validator,
    private val accountLockService: AccountLockService
) {
    private val accounts = ConcurrentHashMap<String, Account>()

    fun createAccount(id: String, owner: String): Account {
        // TODO: 중복 계좌 생성 방지 로직 추가 가능
        validator.validateCreateAccount(id, owner)
        val account = Account(id = id, owner = owner, balance = 0)
        accounts[id] = account
        emailService.sendWelcomeEmail(owner)
        return account
    }

    fun deposit(id: String, amount: Long): Account {
        // TODO: 유효한 입금 금액인지 검증 추가 가능
        val account = accounts[id] ?: throw IllegalArgumentException("Account not found")

        accountLockService.withAccountLock(id){
            account.balance += amount
        }

        return account
    }

    fun withdraw(id: String, amount: Long): Account {

        // user row에 lock을 건다(pessimisticlock)
        // bankService.getPessimisticLock();
        //
        val account = accounts[id] ?: throw IllegalArgumentException("Account not found")
        if (account.balance < amount) throw IllegalArgumentException("Insufficient balance")


        // TODO: 출금 한도 체크, 알림 전송 등 추가 확장 가능
        account.balance -= amount
        return account
    }

    fun getAccount(id: String): Account {
        // TODO: 인증된 사용자만 조회 가능하도록 보안 로직 추가 가능
        return accounts[id] ?: throw AccountNotFoundException(id)
    }
}