package com.example.infrastructure.persistence.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.UUID;

@Builder
@Data
@UserDefinedType("sale_item_entity")
public class SaleItemEntity {

    @PrimaryKey
    private UUID id;
    private String productId;
    private Integer quantity;
    private Double totalValue;
}
