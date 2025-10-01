package com.example.application.usecase.customer;

import com.example.domain.model.Customer;
import com.example.domain.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateCustomerUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCustomerUseCase.class);

    private final CustomerRepository repository;

    public CreateCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer execute(Customer customer) {

        Customer customerSaved = repository.save(customer);
        LOGGER.info("[CreateCustomerUseCase] - Cliente Criado com sucesso: {}", customerSaved.getId());
        return customerSaved;
    }
}
