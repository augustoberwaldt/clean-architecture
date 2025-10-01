package com.example.infrastructure.persistence.converter;

import com.example.domain.model.Product;
import com.example.infrastructure.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProductConverter {

    public ProductEntity convert(Product product) {

        return ProductEntity.builder()
                .id(Optional.ofNullable(product.getId())
                        .map(UUID::fromString)
                        .orElse(null))
                .name(product.getName())
                .description(product.getDescription())
                .value(product.getValue())
                .build();
    }

    public Product convert(ProductEntity productEntity) {

        return Product.builder()
                .id(productEntity.getId().toString())
                .name(productEntity.getName())
                .value(productEntity.getValue())
                .description(productEntity.getDescription())
                .build();
    }
}
