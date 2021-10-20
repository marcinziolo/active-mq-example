package com.southsun.refapp.activemq

import com.ibm.mq.jms.MQConnectionFactory
import com.ibm.msg.client.wmq.WMQConstants
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.converter.SimpleMessageConverter
import javax.jms.ConnectionFactory
import javax.jms.Message

@Configuration
class JmsConfig() {


    @Bean
    fun jmsTemplate(connectionFactory: ConnectionFactory) = JmsTemplate(connectionFactory)

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val cf = MQConnectionFactory()
        cf.queueManager = "QM1"
        cf.channel = "DEV.ADMIN.SVRCONN"
        cf.setStringProperty(WMQConstants.WMQ_CONNECTION_NAME_LIST, "localhost(1414)")
        cf.setStringProperty(WMQConstants.WMQ_CHANNEL, "DEV.ADMIN.SVRCONN")
        cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT)
        cf.setStringProperty(WMQConstants.PASSWORD, "passw0rd")
        cf.setStringProperty(WMQConstants.USERID, "admin")
        return cf
    }
}