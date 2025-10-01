package com.example.infrastructure.persistence.converter;

import com.example.domain.model.Customer;
import com.example.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerConverter {

    public CustomerEntity convert(Customer customer) {

        return CustomerEntity.builder()
                .id(Optional.ofNullable(customer.getId())
                        .map(UUID::fromString)
                        .orElse(null))
                .age(customer.getAge())
                .name(customer.getName())
                .build();
    }

    public Customer convert(CustomerEntity customerEntity) {

        return Customer.builder()
                .id(customerEntity.getId().toString())
                .age(customerEntity.getAge())
                .name(customerEntity.getName())
                .build();
    }
}
