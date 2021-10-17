package com.southsun.refapp.mapper

import com.southsun.refapp.models.Account
import com.southsun.refapp.models.CustomerDetails
import com.southsun.refapp.models.downstream.Response
import com.southsun.refapp.models.downstream.Transaction
import com.southsun.refapp.models.downstream.TransactionType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MappersKtTest {
    @Test
    internal fun mapResponse() {
        val response = Response(
            firstName = "John",
            lastName = "Rambo",
            active = true,
            transactions = listOf(
                Transaction(date = "2021-01-01", amount = "20.0", type = TransactionType.DEPOSIT, accountNumber = "111"),
                Transaction(date = "2021-01-01", amount = "30.0", type = TransactionType.WITHDRAWAL, accountNumber = "111"),
                Transaction(date = "2021-01-01", amount = "40.0", type = TransactionType.DEPOSIT, accountNumber = "111"),
                Transaction(date = "2021-01-01", amount = "50.0", type = TransactionType.DEPOSIT, accountNumber = "222"),
                Transaction(date = "2021-01-01", amount = "30.0", type = TransactionType.WITHDRAWAL, accountNumber = "222"),
            )
        )

        val customerDetails =
        assertEquals(
            CustomerDetails(
                id = null,
                firstName = "John",
                lastName = "Rambo",
                accounts = listOf(
                    Account(accountNumber = "111", balance = "30.0"),
                    Account(accountNumber = "222", balance = "20.0"),
                )
            ),
            response.toCustomerDetails()
        )

    }
}

