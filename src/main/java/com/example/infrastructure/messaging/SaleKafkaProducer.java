package com.example.infrastructure.messaging;

import com.example.application.port.out.SaleProducerPort;
import com.example.domain.model.AddSaleItem;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SaleKafkaProducer implements SaleProducerPort {

    private final KafkaTemplate<String, AddSaleItem> kafkaTemplate;

    private static final String TOPIC_NAME = "adicionar-item-venda";

    public SaleKafkaProducer(KafkaTemplate<String, AddSaleItem> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendSaleItem(AddSaleItem addSaleItem) {
        kafkaTemplate.send(TOPIC_NAME, addSaleItem);
    }
}
