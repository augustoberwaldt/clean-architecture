package com.example.entrypoint.rest.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerResponse {

    private String id;
    private String name;
    private Integer age;
}
