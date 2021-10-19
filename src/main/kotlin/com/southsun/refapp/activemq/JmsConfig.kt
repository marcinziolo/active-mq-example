package com.southsun.refapp.activemq

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.jms.pool.PooledConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.core.JmsMessagingTemplate
import org.springframework.jms.core.JmsTemplate

@Configuration
class JmsConfig {

    @Bean
    fun jmsFactory(): PooledConnectionFactory {
        val pooledConnectionFactory = PooledConnectionFactory()
        pooledConnectionFactory.connectionFactory = ActiveMQConnectionFactory("tcp://localhost:61616")
        return pooledConnectionFactory
    }

    @Bean
    fun jmsTemplate(connectionFactory: PooledConnectionFactory) = JmsTemplate(connectionFactory)

    @Bean
    fun jmsMessagingTemplate(jmsTemplate: JmsTemplate): JmsMessagingTemplate = JmsMessagingTemplate(jmsTemplate)
}