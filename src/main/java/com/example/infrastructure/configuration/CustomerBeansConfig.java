package com.example.infrastructure.configuration;

import com.example.application.usecase.customer.CreateCustomerUseCase;
import com.example.application.usecase.customer.ListCustomersUseCase;
import com.example.application.usecase.customer.SearchCustomerUseCase;
import com.example.application.usecase.customer.UpdateCustomerUseCase;
import com.example.infrastructure.persistence.CustomerImpRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerBeansConfig {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerImpRepository repository) {
        return new CreateCustomerUseCase(repository);
    }

    @Bean
    public SearchCustomerUseCase searchCustomerUseCase(CustomerImpRepository repository) {
        return new SearchCustomerUseCase(repository);
    }

    @Bean
    public ListCustomersUseCase listCustomersUseCase(CustomerImpRepository repository) {
        return new ListCustomersUseCase(repository);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(CustomerImpRepository repository) {
        return new UpdateCustomerUseCase(repository);
    }
}
