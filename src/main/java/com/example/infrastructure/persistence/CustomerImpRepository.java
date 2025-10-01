package com.example.infrastructure.persistence;

import com.example.domain.model.Customer;
import com.example.domain.repository.CustomerRepository;
import com.example.infrastructure.persistence.converter.CustomerConverter;
import com.example.infrastructure.persistence.entity.CustomerEntity;
import com.example.infrastructure.persistence.repository.CustomerDataRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerImpRepository implements CustomerRepository {

    private final CustomerDataRepository repository;
    private final CustomerConverter converter;

    public CustomerImpRepository(CustomerDataRepository repository,
                                 CustomerConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Customer save(Customer customer) {

        CustomerEntity customerEntity = converter.convert(customer);
        if (customerEntity.getId() == null) {
            customerEntity.setId(UUID.randomUUID());
        }
        var customerSaved = this.repository.save(customerEntity);
        return converter.convert(customerSaved);
    }

    @Override
    public Optional<Customer> findById(String id) {
        try {
            Optional<CustomerEntity> customerEntity = this.repository.findById(UUID.fromString(id));
            return customerEntity.map(converter::convert);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Slice<Customer> findAll(Pageable pageable) {
        Slice<CustomerEntity> entities = this.repository.findAll(pageable);
        return entities.map(converter::convert);
    }
}
