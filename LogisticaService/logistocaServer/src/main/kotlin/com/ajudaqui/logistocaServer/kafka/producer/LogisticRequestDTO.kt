package com.ajudaqui.logistocaServer.kafka.producer

import org.apache.kafka.shaded.com.google.protobuf.Timestamp

data class LogisticRequestDTO (val code: String, val volume: Double, val weight: Double, val destination: String)
