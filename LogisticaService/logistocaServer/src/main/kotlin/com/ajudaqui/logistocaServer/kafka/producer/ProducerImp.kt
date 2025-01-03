package com.ajudaqui.logistocaServer.kafka.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.kafka.support.SendResult
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.concurrent.CompletableFuture

@Component
class ProducerImp<K, V : Any>(
    private val template: KafkaTemplate<K, V>
) {

    fun sendMessage(messageId: K, payload: V, topicName: String) {
        val message = createMessageWithHeaders(messageId, payload, topicName)

        val future: CompletableFuture<SendResult<K, V>> = template.send(message)

        future.whenComplete { result, ex ->
            if (ex == null) {
                println("Pessoa enviada com sucesso. MessageId: $messageId")
            } else {
                println("Erro no envio. MessageId: $messageId, erro: ${ex.message}")
            }
        }
    }


    private fun createMessageWithHeaders(messageId: K, payload: V, topic: String):
            Message<Any> {
        return MessageBuilder.withPayload(payload as Any)
            .setHeader("hash", payload.hashCode())
            .setHeader("version", "1.0.0")
            .setHeader("endOfLife", LocalDate.now().plusDays(1L))
            .setHeader("type", "fct")
            .setHeader("cid", messageId)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .setHeader(KafkaHeaders.KEY, messageId)
            .build()
    }
}