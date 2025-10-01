package com.example.stub;

import com.example.domain.model.Sale;
import java.util.Collections;

public class SaleStub {

    public static Sale getSale() {
        return Sale.builder()
            .id("sale-123")
            .clientId("cust-789")
            .totalValue(20.0)
            .items(Collections.singletonList(SaleItemStub.getSaleItem()))
            .build();
    }
}


