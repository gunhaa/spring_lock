package com.example.banking.domain

data class Account(
    val id: String,
    val owner: String,
    var balance: Long = 0
)

