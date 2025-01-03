package com.ajudaqui.logistocaServer

import com.ajudaqui.logistocaServer.kafka.dto.ClosingUpdateDTO
import com.ajudaqui.logistocaServer.kafka.producer.ClosingUpdateProducer
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.UUID

@SpringBootApplication
class LogistocaServerApplication()

fun main(args: Array<String>) {
    runApplication<LogistocaServerApplication>(*args)
}
