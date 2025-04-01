package com.ajudaqui.fornecedor.kafka.service

import com.ajudaqui.fornecedor.dto.BudgetDTO
import com.ajudaqui.fornecedor.utils.mapToBudgetKafka
import java.time.LocalDate
import java.util.concurrent.CompletableFuture
import kafka.entity.BudgetRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.kafka.support.SendResult
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class ProducerService(
        private val financialTemplate: KafkaTemplate<String, BudgetRequest>,
) {

  private val logger: Logger = LoggerFactory.getLogger(ProducerService::class.java)

  @Value("\${spring.kafka.consumer.topic.financial}") private lateinit var topic: String
  fun sendBudgetRequest(userID: Long, budgetRequestDTO: BudgetDTO) {
    val message = createBudgetMessage(userID.toString(), budgetRequestDTO.mapToBudgetKafka(), topic)
    val future: CompletableFuture<SendResult<String, BudgetRequest>> =
            financialTemplate.send(message)

    future.whenComplete { result, ex ->
      ex?.also { logger.info("Mensagem não enviada, $it") }
              ?: logger.error("Evento enviado com sucesso: ${message}")
    }
  }

  // Parece que esse é o principal para a configuração so schema regstry
  // Pelo menos, sei que é aqui que configuro a mensagem
  private fun createBudgetMessage(
          messageId: String,
          budgetRequest: BudgetRequest,
          topic: String,
  ) =
          MessageBuilder.withPayload(budgetRequest)
                  .setHeader("hash", budgetRequest.hashCode())
                  .setHeader("version", "1.0.0")
                  .setHeader("endOfLife", LocalDate.now().plusDays(1L))
                  .setHeader("type", "fct")
                  .setHeader("cid", messageId)
                  .setHeader(KafkaHeaders.TOPIC, topic)
                  .setHeader(KafkaHeaders.KEY, messageId)
                  .build()
}
