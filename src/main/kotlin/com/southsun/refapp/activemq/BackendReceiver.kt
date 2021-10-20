package com.southsun.refapp.activemq

import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.converter.SimpleMessageConverter
import org.springframework.stereotype.Component
import javax.jms.Message

@Component
class BackendReceiver(private val jmsTemplate: JmsTemplate) {

    private val converter = SimpleMessageConverter()

    @JmsListener(destination = "DEV.QUEUE.1")
    fun consume(message: Message) {
        jmsTemplate.send(message.jmsReplyTo) { session ->
            val responseMsg: Message = session.createTextMessage((converter.fromMessage(message) as String).uppercase())
            responseMsg.jmsCorrelationID = message.jmsCorrelationID
            responseMsg
        }
    }
}