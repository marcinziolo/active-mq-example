package com.southsun.refapp.controller

import com.southsun.refapp.models.CustomerDetails
import org.apache.activemq.command.ActiveMQQueue
import org.springframework.jms.core.JmsMessagingTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.jms.DeliveryMode
import javax.jms.Session

@RestController("/")
class HelloWorldController(
    private val jmsMessagingTemplate: JmsMessagingTemplate
) {
    @GetMapping("/{word}")
    fun helloWorld(@PathVariable("word") word: String): String {

        val session: Session = jmsMessagingTemplate.connectionFactory!!.createConnection()
            .createSession(false, Session.AUTO_ACKNOWLEDGE)

        val objectMessage = session.createObjectMessage(word)

        objectMessage.jmsCorrelationID = UUID.randomUUID().toString();
        objectMessage.jmsReplyTo = ActiveMQQueue("response")
        objectMessage.jmsExpiration = 10000L;
        objectMessage.jmsDeliveryMode = DeliveryMode.NON_PERSISTENT;


        return jmsMessagingTemplate.convertSendAndReceive(
            ActiveMQQueue("request"),
            objectMessage, String::class.java
        )!!
    }
}