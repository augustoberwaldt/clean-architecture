package com.example.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Sale {
    private String id;
    private String clientId;
    private Double totalValue;
    private List<SaleItem> items;
}
