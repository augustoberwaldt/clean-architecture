package com.example.application.port.out;

import com.example.domain.model.AddSaleItem;

public interface SaleProducerPort {

    void sendSaleItem(AddSaleItem addSaleItem);
}
