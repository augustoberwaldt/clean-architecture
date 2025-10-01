package com.example.domain.repository;

import com.example.domain.model.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(String id);

    Slice<Customer> findAll(Pageable pageable);
}
