package com.ajudaqui.logistocaServer.service

import com.ajudaqui.logistocaServer.kafka.dto.LogisticRequestDTO
import com.ajudaqui.logistocaServer.kafka.entity.LogisticRequest
import com.ajudaqui.logistocaServer.kafka.producer.ProducerImp
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LogisticRequestService(private val opa: ProducerImp<String, LogisticRequest>) {
    @Value("\${kafka.topic.logistics.request}")
    private lateinit var topicName: String


    fun persist(messageId: String, payload: LogisticRequestDTO) {
        val logisticRequest: LogisticRequest = createDTO(payload)
        opa.sendMessage(messageId, logisticRequest, topicName)
    }

    private fun createDTO(payload: LogisticRequestDTO): LogisticRequest =
        LogisticRequest.newBuilder()
            .setCode(payload.code)
            .setVolume(payload.volume)
            .setWeight(payload.weight)
            .setDestination(payload.destination)
            .setTimestamp(LocalDateTime.now().toString())
            .build()
}