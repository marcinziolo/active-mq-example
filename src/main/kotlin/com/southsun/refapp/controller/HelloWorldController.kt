package com.southsun.refapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class HelloWorldController {
    @GetMapping
    fun helloWorld() = "Hello World!!"
}