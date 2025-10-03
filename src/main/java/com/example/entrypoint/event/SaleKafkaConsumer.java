package com.example.entrypoint.event;


import com.example.application.usecase.sale.ConsumeSaleItemSaleUseCase;
import com.example.domain.model.AddSaleItem;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.util.backoff.FixedBackOff;

@RequiredArgsConstructor
@EnableKafka
@Component
public class SaleKafkaConsumer {

    private static final String TOPIC_DLQ_NAME = "adicionar-item-venda-dlq";
    private static final String TOPIC_NAME = "adicionar-item-venda";

    private final ConsumeSaleItemSaleUseCase consumeSaleItemSaleUseCase;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(AddSaleItem addSaleItem) {
        try {
            consumeSaleItemSaleUseCase.execute(addSaleItem);
        } catch (Exception e) {
            throw e;
        }
    }
}
