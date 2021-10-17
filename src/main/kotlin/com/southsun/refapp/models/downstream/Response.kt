package com.southsun.refapp.models.downstream


data class Response(
    val firstName: String,
    val lastName: String,
    val active: Boolean,
    val transactions: List<Transaction>
)

data class Transaction(
    val date: String,
    val amount: String,
    val type: TransactionType,
    val accountNumber: String
)

enum class TransactionType {
    WITHDRAWAL,
    DEPOSIT
}