package com.example.entrypoint.rest.request;

import lombok.Data;

@Data
public class AddItemSaleRequest {

    private String productId;
    private Integer quantity;
}
