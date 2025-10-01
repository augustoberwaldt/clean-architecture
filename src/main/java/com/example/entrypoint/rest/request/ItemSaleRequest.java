package com.example.entrypoint.rest.request;

import lombok.Data;

@Data
public class ItemSaleRequest {

    private String productId;
    private Integer quantity;
    private Double totalValue;
}
