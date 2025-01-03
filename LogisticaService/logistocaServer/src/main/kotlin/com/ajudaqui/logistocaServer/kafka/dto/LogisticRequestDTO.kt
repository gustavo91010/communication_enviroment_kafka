package com.ajudaqui.logistocaServer.kafka.dto

data class LogisticRequestDTO (val code: String, val volume: Double, val weight: Double, val destination: String)
