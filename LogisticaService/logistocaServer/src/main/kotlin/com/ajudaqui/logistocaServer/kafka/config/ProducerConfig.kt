package com.ajudaqui.logistocaServer.kafka.config

import com.ajudaqui.logistocaServer.kafka.entity.LogisticRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class ProducerConfig {
    @Bean
    fun <K, V> template(factor: ProducerFactory<K, V>): KafkaTemplate<K, V> =
        KafkaTemplate(factor)
}