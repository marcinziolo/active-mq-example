package com.southsun.refapp.models.downstream


class Response(
    private val firstName: String,
    private val lastName: String,
    private val active: Boolean,
    private val transactions: List<Transaction>
)

data class Transaction(
    private val date: String,
    private val amount: String,
    private val type: TransactionType,
    private val accountNumber: String
)

enum class TransactionType {
    WITHDRAWAL,
    DEPOSIT
}