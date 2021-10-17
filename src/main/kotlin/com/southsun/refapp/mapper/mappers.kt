package com.southsun.refapp.mapper

import com.southsun.refapp.models.Account
import com.southsun.refapp.models.CustomerDetails
import com.southsun.refapp.models.Request
import com.southsun.refapp.models.downstream.Response
import com.southsun.refapp.models.downstream.TransactionType
import java.time.LocalDate
import java.util.*
import java.util.Calendar.DAY_OF_MONTH
import com.southsun.refapp.models.downstream.Request as DownstreamRequest

fun Request.toDownstream() {
    DownstreamRequest(
        customerId = customerId,
        startDate = LocalDate.of(year as Int, (month ?: 1) as Int, 1).toString(),
        endDate = LocalDate.of(year as Int, (month ?: 1) as Int, Calendar.getInstance().getActualMaximum(DAY_OF_MONTH))
            .toString()
    )
}

fun Response.toCustomerDetails() = CustomerDetails(
    firstName = firstName,
    lastName = lastName,
    accounts = transactions.groupBy { it.accountNumber }
        .map {
            Account(
                accountNumber = it.key,
                balance = it.value.map {
                    if (it.type == TransactionType.DEPOSIT)
                        it.amount.toDouble()
                    else
                        it.amount.toDouble() * -1
                }.sum().toString()
            )
        }
)