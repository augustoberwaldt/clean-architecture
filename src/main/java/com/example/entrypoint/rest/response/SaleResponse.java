package com.example.entrypoint.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SaleResponse {

    private String id;
    private String clientId;
    private Double totalValue;
    private List<ItemSaleResponse> items;
}
