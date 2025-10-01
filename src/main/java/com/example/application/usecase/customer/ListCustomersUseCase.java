package com.example.application.usecase.customer;

import com.example.domain.model.Customer;
import com.example.domain.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public class ListCustomersUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListCustomersUseCase.class);

    private final CustomerRepository repository;

    public ListCustomersUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Slice<Customer> execute(Pageable pageable) {

        Slice<Customer> customers = repository.findAll(pageable);
        LOGGER.info("Total customers found: {}", customers.getNumberOfElements());
        return customers;
    }
}
