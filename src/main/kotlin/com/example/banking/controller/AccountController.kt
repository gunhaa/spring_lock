package com.example.banking.controller

import com.example.banking.domain.Account
import com.example.banking.service.AccountService
import com.example.banking.validator.Validator
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping
    fun createAccount(@RequestParam id: String, @RequestParam owner: String): ResponseEntity<Account> {
        // TODO: 요청 파라미터 검증 추가
        val account = accountService.createAccount(id, owner);
        return ResponseEntity.ok(account)
    }

    @PostMapping("/{id}/deposit")
    fun deposit(@PathVariable id: String, @RequestParam amount: Long): ResponseEntity<Account> {
        // TODO: amount 유효성 체크, 음수 금지 등
        val account = accountService.deposit(id, amount)
        return ResponseEntity.ok(account)
    }

    @PostMapping("/{id}/withdraw")
    fun withdraw(@PathVariable id: String, @RequestParam amount: Long): ResponseEntity<Account> {
        // TODO: 예외 발생 시 적절한 HTTP 응답 코드로 변환
        val account = accountService.withdraw(id, amount)
        return ResponseEntity.ok(account)
    }

    @GetMapping("/{id}")
    fun getAccount(@PathVariable id: String): ResponseEntity<Account> {
        // TODO: 조회 권한 확인 또는 인증 처리
        val account = accountService.getAccount(id)
        return ResponseEntity.ok(account)
    }
}