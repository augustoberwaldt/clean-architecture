package com.example.stub;

import com.example.domain.model.SaleItem;

public class SaleItemStub {

    public static SaleItem.SaleItemBuilder getSaleItem() {

        return SaleItem.builder()
                .productId("prod-456")
                .quantity(2)
                .totalValue(20.0);
    }
}

