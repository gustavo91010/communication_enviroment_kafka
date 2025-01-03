package com.ajudaqui.logistocaServer.kafka.dto

data class LogisticUpdatedDTO(
    val code: String,
    val departureDate: String,
    val estimatedArrivalDate: String,
    val trackingCode: String
)
