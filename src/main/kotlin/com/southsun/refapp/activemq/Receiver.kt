package com.southsun.refapp.activemq

import org.apache.activemq.command.ActiveMQObjectMessage
import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.listener.SessionAwareMessageListener
import org.springframework.stereotype.Component
import java.util.*
import javax.jms.JMSException
import javax.jms.Message
import javax.jms.MessageProducer
import javax.jms.ObjectMessage
import javax.jms.Session


@Component
class Receiver : SessionAwareMessageListener<Message> {
    @JmsListener(destination = "request")
    @Throws(JMSException::class)
    override fun onMessage(message: Message, session: Session) {
//
        val request = (message as ActiveMQObjectMessage).`object` as String
        print("Received message $request")

        // done handling the request, now create a response message
        val responseMessage: ObjectMessage = ActiveMQObjectMessage()
        responseMessage.setJMSCorrelationID(message.getJMSCorrelationID())
        responseMessage.setObject(request.uppercase(Locale.getDefault()))

        // Message sent back to the replyTo address of the income message.
        val producer: MessageProducer = session.createProducer(message.getJMSReplyTo())
        producer.send(responseMessage)
    }
}