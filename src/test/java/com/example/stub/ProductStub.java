package com.example.stub;

import com.example.entrypoint.rest.request.ProductRequest;

public class ProductStub {

    public static ProductRequest getProductRequest() {

        ProductRequest request = new ProductRequest();
        request.setName("Test Product");
        request.setDescription("Test Description");
        request.setValue(10.0);

        return request;
    }
}
