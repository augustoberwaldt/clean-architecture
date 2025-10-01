package com.example.infrastructure.persistence.converter;

import com.example.domain.model.Sale;
import com.example.domain.model.SaleItem;
import com.example.infrastructure.persistence.entity.SaleEntity;
import com.example.infrastructure.persistence.entity.SaleItemEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SaleConverter {

    public SaleEntity convert(Sale sale) {

        return SaleEntity.builder()
                .id(Optional.ofNullable(sale.getId())
                        .map(UUID::fromString)
                        .orElse(null))
                .clientId(sale.getClientId())
                .totalValue(sale.getTotalValue())
                .items(getListItem(sale))
                .build();
    }

    public Sale convert(SaleEntity saleEntity) {

        return Sale.builder()
                .id(saleEntity.getId().toString())
                .clientId(saleEntity.getClientId())
                .totalValue(saleEntity.getTotalValue())
                .items(getListItem(saleEntity))
                .build();
    }

    private List<SaleItemEntity> getListItem(Sale sale) {

        return sale.getItems()
                .stream()
                .map(self -> SaleItemEntity.builder()
                        .productId(self.getProductId())
                        .quantity(self.getQuantity())
                        .totalValue(self.getTotalValue())
                        .build())
                .toList();
    }

    private List<SaleItem> getListItem(SaleEntity entity) {

        return entity.getItems()
                .stream()
                .map(self -> SaleItem.builder()
                        .productId(self.getProductId())
                        .quantity(self.getQuantity())
                        .totalValue(self.getTotalValue())
                        .build())
                .toList();
    }
}
