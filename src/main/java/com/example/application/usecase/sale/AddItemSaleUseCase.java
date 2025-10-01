package com.example.application.usecase.sale;

import com.example.application.port.out.SaleProducerPort;
import com.example.domain.model.AddSaleItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddItemSaleUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddItemSaleUseCase.class);

    private final SaleProducerPort saleProducerPort;

    public AddItemSaleUseCase(SaleProducerPort saleProducerPort) {
        this.saleProducerPort = saleProducerPort;
    }

    public AddSaleItem execute(AddSaleItem addSaleItem) {

        saleProducerPort.sendSaleItem(addSaleItem);
        LOGGER.info("AddItemSaleUseCase.execute - [{}]", addSaleItem);
        return addSaleItem;
    }
}
