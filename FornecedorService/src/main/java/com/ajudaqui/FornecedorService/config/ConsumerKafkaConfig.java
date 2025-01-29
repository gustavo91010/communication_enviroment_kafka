package com.ajudaqui.FornecedorService.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.ajudaqui.FornecedorService.avro.Order_2;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

@Configuration
public class ConsumerKafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers:localhost:29092}")
    private String bootstrapAddress;

    // @Bean
    // public ProducerFactory<String, Order> producerFactory() {

    // Map<String, Object> configProps = new HashMap<>();
    // configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    // configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
    // StringSerializer.class);
    // configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
    // JsonSerializer.class);

    // return new DefaultKafkaProducerFactory<>(configProps);
    // }
    // @Bean
    // public KafkaTemplate<String, Order> kafkaTemplate()
    // {
    // return new KafkaTemplate<>(producerFactory());
    // }

    @Bean
    public ConsumerFactory<String, Order_2> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                "schema.registry.url",
                "http://localhost:8085");

        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                KafkaAvroDeserializer.class);
        props.put(
                KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG,
                true);

        props.put(
                JsonDeserializer.TRUSTED_PACKAGES,
                "*");// definir os pacotes dos tipos de objeto que devem ser deserializados, no caso,
                     // todos
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);// Define a quantidade de mensagems que é pega por vez,
                                                              // por padraõ, pode set ate 500, ams quanto menor mais
                                                              // repido o processamento
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Apartir de quando, apos asubir a aplicação
                                                                        // ele vai pegar as mensagens
        // latest pega apartir do momento em que subiu
        // earliest pega desde a primeira

        props.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, false); // define se pode criar topicos novos ou não,
                                                                          // por padrão, é true
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);// comite apos o prpocessamento da mensage
        // Com isso, o kafka só vai saber que eu recebi a mensagen, se eu, for la em
        // commitar avisando
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
