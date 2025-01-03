package com.ajudaqui.logistocaServer.kafka.dto

import com.ajudaqui.logistocaServer.kafka.entity.LogisticRequest
import java.time.LocalDateTime

data class LogisticRequestDTO(
    val code: String,
    val volume: Double,
    val weight: Double,
    val destination: String,
    val timestamp: String
){
    companion object {
        fun fromLogisticRequestAvro(avro: LogisticRequest): LogisticRequestDTO {
            return LogisticRequestDTO(
                code = avro.getCode().toString(),
                volume = avro.getVolume(),
                weight = avro.getWeight(),
                destination = avro.getDestination().toString(),
                timestamp = LocalDateTime.now().toString()
            )
        }
    }
}


