package com.ajudaqui.fornecedor.kafka.service

import kafka.entity.Order
import com.ajudaqui.fornecedor.service.OrderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import org.springframework.kafka.annotation.KafkaListener

@Component
class Consumer (
  private val orderService: OrderService
){
  private val logger: Logger = LoggerFactory.getLogger(Consumer::class.java)

   @KafkaListener(topics = ["\${spring.kafka.consumer.topic.order}"], groupId = "gestor-consumer")
  fun consumer(@Payload order: Order) {
    logger.info("Mensagem recebida: ${orderService.generatedOrder(order)}")
  }
}
