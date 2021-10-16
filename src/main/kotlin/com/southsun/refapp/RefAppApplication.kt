package com.southsun.refapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RefAppApplication

fun main(args: Array<String>) {
    runApplication<RefAppApplication>(*args)
}
