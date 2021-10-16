package com.southsun.refapp.models

data class Response(
    private val firstName: String,
    private val lastName: String,
    private val accounts: List<Account>
)

data class Account(
    private val accountNumber: String,
    private val totalBalance: String
)