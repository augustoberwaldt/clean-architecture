package com.example.application.usecase.customer;

import com.example.domain.exception.NotFoundBusinessException;
import com.example.domain.model.Customer;
import com.example.domain.repository.CustomerRepository;
import com.example.infrastructure.persistence.CustomerImpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchCustomerUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchCustomerUseCase.class);

    private final CustomerRepository repository;

    private static final String CUSTOMER_NOT_FOUND = "Cliente não encontrado.";

    public SearchCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer execute(String id) {

        Customer customer = repository.findById(id)
                .orElseThrow(() -> new NotFoundBusinessException(CUSTOMER_NOT_FOUND));

        LOGGER.info("SearchCustomerUseCase.execute - [{}]", customer.getId());
        return customer;
    }
}
