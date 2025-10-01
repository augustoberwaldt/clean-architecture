package com.example.entrypoint.rest.mapper;

import com.example.domain.model.Customer;
import com.example.entrypoint.rest.request.CustomerRequest;
import com.example.entrypoint.rest.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer from(CustomerRequest customerRequest) {

        return Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .build();
    }

    public CustomerResponse from(Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .build();
    }
}
