package com.example.stub;

import com.example.domain.model.AddSaleItem;

public class AddSaleItemStub {

    public static AddSaleItem getAddSaleItem() {
        return AddSaleItem.builder()
                .saleId("sale-123")
                .productId("prod-456")
                .quantity(2)
                .build();
    }
}