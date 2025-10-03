package com.example.stub;

import com.example.domain.model.Customer;
import com.example.entrypoint.rest.response.CustomerResponse;

public class CustomerStub {

    public static Customer.CustomerBuilder getCustomer() {

        return Customer.builder()
            .id("cust-789")
            .name("Test Customer")
            .age(30);
    }

    public static CustomerResponse.CustomerResponseBuilder getCustomerResponse() {

        return CustomerResponse.builder()
                .name("Test Customer")
                .age(30);
    }
}

