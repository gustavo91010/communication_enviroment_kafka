package com.ajudaqui.logistocaServer.kafka.producer

import com.ajudaqui.logistocaServer.kafka.dto.LogisticUpdatedDTO
import com.ajudaqui.logistocaServer.kafka.entity.LogisticUpdated
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Component
class LogisticUpdatedProducer(private val producerImp: ProducerImp<String, LogisticUpdated>) {

    @Value("\${kafka.topic.logistics.update}")
    private lateinit var topicName: String

    fun persist(messageId: String, payload: LogisticUpdatedDTO) {
        val logisticUpdated: LogisticUpdated = createDTO(payload)
        producerImp.sendMessage(messageId, logisticUpdated, topicName)
    }
    private fun createDTO(payload: LogisticUpdatedDTO): LogisticUpdated =
        LogisticUpdated.newBuilder()
            .setCode(payload.code)
            .setDepartureDate(LocalDateTime.now().toString())
            .setTrackingCode(UUID.randomUUID().toString())
            .setEstimatedArrivalDate(LocalDateTime.now().plusDays(3).toString())
            .setTimestamp(LocalDateTime.now().toString())
            .build()
}