package com.example.infrastructure.persistence.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@Table("vendas")
public class SaleEntity {

    @PrimaryKey
    private UUID id;
    private String clientId;
    private Double totalValue;
    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.UDT, userTypeName = "sale_item_entity")
    List<SaleItemEntity> items;
}
