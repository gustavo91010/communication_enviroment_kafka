package com.ajudaqui.logistocaServer.kafka.producer

import com.ajudaqui.logistocaServer.kafka.entity.LogisticRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.kafka.support.SendResult
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture

@Component
class LogisticProducerImp (
    private val template: KafkaTemplate<String, LogisticRequest>
){
    @Value("\${kafka.topic.logistics.request}")
    private lateinit var topicName: String

    fun persist(messageId: String, payload: LogisticRequestDTO) {
        println("topicName $topicName")
        val logisticRequest: LogisticRequest = createDTO(payload)
        sendMessage(messageId, logisticRequest)
    }
    private fun sendMessage(messageId: String, dto: LogisticRequest) {
        val message = createMessageWithHeaders(messageId, dto, topicName)

        val future: CompletableFuture<SendResult<String, LogisticRequest>> = template.send(message)

        future.whenComplete { result, ex ->
            if (ex == null) {
                println("Pessoa enviada com sucesso. MessageId: $messageId")
            } else {
                println("Erro no envio. MessageId: $messageId, erro: ${ex.message}")
            }
        }
    }

    private fun createDTO(payload: LogisticRequestDTO): LogisticRequest =
        LogisticRequest.newBuilder()
            .setCode(payload.code)
            .setVolume(payload.volume)
            .setWeight(payload.weight)
            .setDestination(payload.destination)
            .setTimestamp(LocalDateTime.now().toString())
            .build()

    private fun createMessageWithHeaders(messageId: String, logisticRequest: LogisticRequest, topic: String):
            Message<LogisticRequest> {
        return MessageBuilder.withPayload(logisticRequest)
            .setHeader("hash", logisticRequest.hashCode())
            .setHeader("version", "1.0.0")
            .setHeader("endOfLife", LocalDate.now().plusDays(1L))
            .setHeader("type", "fct")
            .setHeader("cid", messageId)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader(KafkaHeaders.KEY, messageId)
            .build()
    }
}