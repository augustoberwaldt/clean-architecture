package com.example.application.usecase.customer;

import com.example.domain.exception.NotFoundBusinessException;
import com.example.domain.model.Customer;
import com.example.domain.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateCustomerUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCustomerUseCase.class);

    private final CustomerRepository repository;

    private static final String CUSTOMER_NOT_FOUND = "Cliente não encontrado.";

    public UpdateCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer execute(String id, Customer customer) {

        Customer customerSaved = repository.findById(id)
                .orElseThrow(() -> new NotFoundBusinessException(CUSTOMER_NOT_FOUND));

        customer.setId(customerSaved.getId());
        repository.save(customer);

        LOGGER.info("Cliente atualizado com sucesso: {}", customer);
        return customer;
    }
}

