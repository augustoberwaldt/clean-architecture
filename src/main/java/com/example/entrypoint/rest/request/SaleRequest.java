package com.example.entrypoint.rest.request;

import lombok.Data;

import java.util.List;

@Data
public class SaleRequest {

    private String clientId;

    private Double totalValue;

    private List<ItemSaleRequest> items;
}
