package com.example.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Customer {

    private String id;
    private String name;
    private Integer age;
}
