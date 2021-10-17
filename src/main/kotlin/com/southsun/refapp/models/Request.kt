package com.southsun.refapp.models

data class Request(
    private val customerId: String,
    private val month: String?,
    private val year: String
)