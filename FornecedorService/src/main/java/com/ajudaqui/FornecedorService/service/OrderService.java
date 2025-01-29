package com.ajudaqui.FornecedorService.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.ajudaqui.FornecedorService.avro.Order;

/**
 * FornecedorService
 */
@Service
public class OrderService {

    @RetryableTopic(backoff = @Backoff(value = 5000L), attempts = "5", autoCreateTopics = "true"

    )
    @KafkaListener(topics = "client.order.request", groupId = "fornecedor_01")
    public void consumeOrder(Order order, Acknowledgment acknowledgment) {
        acknowledgment.acknowledge();// comitando avisando que recebi a mensagem
        System.out.println("Order recebida com sucesso! " + order);

    }
}
