package com.ajudaqui.logistocaServer.kafka.producer

import com.ajudaqui.logistocaServer.kafka.dto.ClosingUpdateDTO
import com.ajudaqui.logistocaServer.kafka.entity.ClosingUpdate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Component
class ClosingUpdateProducer(private val producerImp: ProducerImp<String, ClosingUpdate>) {

    @Value("\${kafka.topic.logistics.close}")
    private lateinit var topicName: String

    fun persist(messageId: String, payload: ClosingUpdateDTO) {
        val closingUpdate: ClosingUpdate = createDTO(payload)
        producerImp.sendMessage(messageId, closingUpdate, topicName)
    }
    private fun createDTO(payload: ClosingUpdateDTO): ClosingUpdate =
        ClosingUpdate.newBuilder()
            .setCode(payload.code)
            .setStatus(payload.status)
            .setTimestamp(LocalDateTime.now().toString())
            .build()
}