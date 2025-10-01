package com.example.entrypoint.rest.mapper;


import com.example.domain.model.AddSaleItem;
import com.example.domain.model.Sale;
import com.example.domain.model.SaleItem;
import com.example.entrypoint.rest.request.AddItemSaleRequest;
import com.example.entrypoint.rest.request.SaleRequest;
import com.example.entrypoint.rest.response.ItemSaleResponse;
import com.example.entrypoint.rest.response.SaleResponse;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SaleMapper {

    public Sale from(SaleRequest request) {

        return Sale.builder()
                .clientId(request.getClientId())
                .totalValue(request.getTotalValue())
                .items(getListItem(request))
                .build();
    }

    public AddSaleItem from(String saleId, AddItemSaleRequest request) {

        return AddSaleItem.builder()
                .saleId(saleId)
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .build();
    }

    private List<SaleItem> getListItem(SaleRequest request) {

        return request.getItems()
                .stream()
                .map(self -> SaleItem.builder()
                        .productId(self.getProductId())
                        .quantity(self.getQuantity())
                        .totalValue(self.getTotalValue())
                        .build())
                .toList();
    }

    public SaleResponse from(Sale sale) {

        return SaleResponse.builder()
                .items(getListItemResponse(sale))
                .totalValue(sale.getTotalValue())
                .clientId(sale.getClientId())
                .id(sale.getId())
                .build();
    }

    private List<ItemSaleResponse> getListItemResponse(Sale sale) {

        return sale.getItems()
                .stream()
                .map(self -> ItemSaleResponse.builder()
                        .productId(self.getProductId())
                        .quantity(self.getQuantity())
                        .totalValue(self.getTotalValue())
                        .build())
                .toList();
    }
}
