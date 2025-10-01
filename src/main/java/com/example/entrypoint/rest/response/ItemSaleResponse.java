package com.example.entrypoint.rest.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemSaleResponse {

    private String productId;
    private Integer quantity;
    private Double totalValue;
}
