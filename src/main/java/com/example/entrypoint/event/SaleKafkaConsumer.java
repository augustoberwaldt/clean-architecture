package com.example.entrypoint.event;


import com.example.application.usecase.sale.ConsumeSaleItemSaleUseCase;
import com.example.domain.model.AddSaleItem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@DependsOn("cassandraMigrationRunner")
@RequiredArgsConstructor
@Component
public class SaleKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleKafkaConsumer.class);

    private static final String TOPIC_NAME = "adicionar-item-venda";

    private final ConsumeSaleItemSaleUseCase consumeSaleItemSaleUseCase;

    @Value("${spring.kafka.consumer.group-id")
    private String groupId;

    @KafkaListener(topics = TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(AddSaleItem addSaleItem) {

        consumeSaleItemSaleUseCase.execute(addSaleItem);
    }
}
