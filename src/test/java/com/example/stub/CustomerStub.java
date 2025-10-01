package com.example.stub;

import com.example.domain.model.Customer;

public class CustomerStub {
    public static Customer getCustomer() {
        return Customer.builder()
            .id("cust-789")
            .name("Test Customer")
            .age(30)
            .build();
    }
}

