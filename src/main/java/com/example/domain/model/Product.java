package com.example.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {

    private String id;
    private String name;
    private String description;
    private Double value;
}
