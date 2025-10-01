package com.example.infrastructure.persistence.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Builder
@Data
@Table("produtos")
public class ProductEntity {

    @PrimaryKey
    private UUID id;
    private String name;
    private String description;
    private Double value;
}
