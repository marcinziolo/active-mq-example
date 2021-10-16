package com.southsun.refapp.models.downstream

data class Request(
    private val customerId: String,
    private val startDate: String,
    private val endDate: String
)