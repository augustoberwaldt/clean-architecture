package com.example.entrypoint.rest.mapper;

import com.example.domain.model.Product;
import com.example.entrypoint.rest.request.ProductRequest;
import com.example.entrypoint.rest.response.ProductResponse;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public Product from(ProductRequest request) {

        return Product.builder()
                .description(request.getDescription())
                .name(request.getName())
                .value(request.getValue())
                .build();
    }

    public ProductResponse from(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .value(product.getValue())
                .build();
    }
}
