package com.example.entrypoint.rest.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private Double value;
}