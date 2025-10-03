package com.example.entrypoint.event;

import com.example.domain.model.AddSaleItem;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class SaleKafkaConsumerConfig {
    private static final String TOPIC_DLQ_NAME = "adicionar-item-venda-dlq";

    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate<String, Object> kafkaTemplate) {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
            (record, ex) -> new TopicPartition(TOPIC_DLQ_NAME, record.partition()));
        return new DefaultErrorHandler(recoverer, new FixedBackOff(0L, 2));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AddSaleItem> kafkaListenerContainerFactory(
            ConsumerFactory<String, AddSaleItem> consumerFactory,
            DefaultErrorHandler errorHandler) {
        ConcurrentKafkaListenerContainerFactory<String, AddSaleItem> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }
}

