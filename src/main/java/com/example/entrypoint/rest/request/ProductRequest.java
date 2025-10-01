package com.example.entrypoint.rest.request;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private Double value;
}
