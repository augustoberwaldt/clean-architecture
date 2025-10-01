package com.example.domain.model;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class SaleItem {

    private String productId;
    private Integer quantity;
    private Double totalValue;
}
