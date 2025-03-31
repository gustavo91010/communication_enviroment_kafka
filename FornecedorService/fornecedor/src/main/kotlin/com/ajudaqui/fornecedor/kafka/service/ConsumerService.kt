package com.ajudaqui.fornecedor.kafka.service

import com.ajudaqui.fornecedor.service.FinancialService
import com.ajudaqui.fornecedor.service.OrderService
import kafka.entity.Order
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class Consumer(
        private val orderService: OrderService,
        private val financialService: FinancialService,
        private val producer: ProducerService
) {
  private val logger: Logger = LoggerFactory.getLogger(Consumer::class.java)

  @KafkaListener(topics = ["\${spring.kafka.consumer.topic.order}"], groupId = "gestor-consumer")
  fun consumer(@Payload order: Order) {
    logger.info("Ordem recebida: ${orderService.mapOrder(order)}")
    var lalala = orderService.mapOrder(order)
    var lelele = financialService.generatedBudget(lalala)
    producer.sendBudgetRequest(7L, lelele)
    logger.info("orçamento enviado: $lelele")
  }
}
