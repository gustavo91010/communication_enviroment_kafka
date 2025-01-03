package com.ajudaqui.logistocaServer

import com.ajudaqui.logistocaServer.kafka.producer.LogisticProducerImp
import com.ajudaqui.logistocaServer.kafka.producer.LogisticRequestDTO
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.UUID

@SpringBootApplication
class LogistocaServerApplication(val lalala: LogisticProducerImp) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val payload = LogisticRequestDTO(
            code = UUID.randomUUID().toString(),
            weight = 12.6,
            volume = 555.8,
            destination = "Rua sargento jão lopes filho 76")
        lalala.persist("12345", payload)
        println("bem.. se saio aqui, é bom sinal...")
    }

}

fun main(args: Array<String>) {
    runApplication<LogistocaServerApplication>(*args)
}
