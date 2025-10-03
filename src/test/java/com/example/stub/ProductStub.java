package com.example.stub;

import com.example.domain.model.Product;
import com.example.entrypoint.rest.request.ProductRequest;
import com.example.infrastructure.persistence.entity.ProductEntity;

import java.util.UUID;

public class ProductStub {

    public static ProductRequest getProductRequest() {

        ProductRequest request = new ProductRequest();
        request.setName("Test Product");
        request.setDescription("Test Description");
        request.setValue(10.0);

        return request;
    }

    public static ProductEntity.ProductEntityBuilder getProductEntity() {

        return ProductEntity.builder()
                .name("Test Product")
                .description("Test Description")
                .value(10.0);
    }

    public static Product.ProductBuilder getProduct() {

        return Product.builder()
                .name("Test Product")
                .description("Test Description")
                .value(10.0);
    }
}
