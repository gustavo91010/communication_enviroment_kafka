package kafka.config

import kafka.entity.BudgetRequest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class ProducerConfig {

    @Bean
  fun financialProducerTemplate(
          factor: ProducerFactory<String, BudgetRequest>
  ): KafkaTemplate<String, BudgetRequest> = KafkaTemplate(factor)
}
