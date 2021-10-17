package com.southsun.refapp.controller

import com.southsun.refapp.models.CustomerDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController("/")
class HelloWorldController {
    @GetMapping
    fun helloWorld() = CustomerDetails(
        id = UUID.randomUUID().toString(),
        firstName = "John",
        lastName = "Rambo",
        accounts = emptyList()
    )
}