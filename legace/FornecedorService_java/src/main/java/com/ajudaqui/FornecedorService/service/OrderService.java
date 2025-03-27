package com.ajudaqui.FornecedorService.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.ajudaqui.FornecedorService.avro.Order_2;


/**
 * FornecedorService
 */
@Service
public class OrderService {

    @RetryableTopic(backoff = @Backoff(value = 5000L), attempts = "5", autoCreateTopics = "true"

    )
    @KafkaListener(topics = "client.order.request", groupId = "fornecedor_02")
    public void consumeOrder(Order_2 order) {
        System.out.println("cheguei aqui...");
        System.out.println("Order recebida com sucesso! " + order);

    }
}
