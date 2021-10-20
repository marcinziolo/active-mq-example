package com.southsun.refapp.controller

import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator
import org.springframework.jms.support.converter.SimpleMessageConverter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.jms.TextMessage


@RestController("/")
class HelloWorldController(
    private val jmsTemplate: JmsTemplate,
) {

    private val converter = SimpleMessageConverter()

    @GetMapping("/{word}")
    fun send(@PathVariable("word") word: String): String {
        val message = jmsTemplate.sendAndReceive("DEV.QUEUE.1", MessageCreator {
            val message: TextMessage = it.createTextMessage(word)
            message.jmsCorrelationID = UUID.randomUUID().toString()
            message.jmsReplyTo = it.createQueue("DEV.QUEUE.2")
            message
        })!!

        return converter.fromMessage(message) as String
    }
}