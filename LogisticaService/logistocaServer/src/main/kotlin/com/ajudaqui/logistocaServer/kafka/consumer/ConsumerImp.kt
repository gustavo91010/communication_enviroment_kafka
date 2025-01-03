package com.ajudaqui.logistocaServer.kafka.consumer

import com.ajudaqui.logistocaServer.kafka.dto.ClosingUpdateDTO
import com.ajudaqui.logistocaServer.kafka.dto.LogisticRequestDTO
import com.ajudaqui.logistocaServer.kafka.dto.LogisticUpdatedDTO
import com.ajudaqui.logistocaServer.kafka.entity.LogisticRequest
import com.ajudaqui.logistocaServer.kafka.producer.ClosingUpdateProducer
import com.ajudaqui.logistocaServer.kafka.producer.LogisticUpdatedProducer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class ConsumerImp(
    private val logisticUpdatedProducer: LogisticUpdatedProducer,
    private val closingUpdateProducer: ClosingUpdateProducer
) {
    private val logger: Logger = LoggerFactory.getLogger(ConsumerImp::class.java)

    @KafkaListener(topics = ["\${kafka.topic.logistics.request}"], groupId = "logistic-consumer")
    fun consumerLogisticRequest(@Payload logisticRequest: LogisticRequest) {
        val request = LogisticRequestDTO.fromLogisticRequestAvro(logisticRequest)

        logger.info("Logistic request: $request")
        generatedProducer(request)
    }

    fun generatedProducer(logisticRequest: LogisticRequestDTO) {
        val logisticUpdatedDTO = LogisticUpdatedDTO(logisticRequest.code)

        logisticUpdatedProducer.persist("123456", logisticUpdatedDTO)
        Thread.sleep(30000)

        val closingUpdateDTO = ClosingUpdateDTO(logisticRequest.code, "FINISH")
        closingUpdateProducer.persist("123456", closingUpdateDTO)
    }
}