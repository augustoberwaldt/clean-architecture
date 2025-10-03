package com.example.application.usecase.customer;

import com.example.domain.exception.NotFoundBusinessException;
import com.example.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.stub.CustomerStub;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SearchCustomerUseCaseTest {

    @Test
    void execute() {
        CustomerRepository repository = mock(CustomerRepository.class);
        var customer = CustomerStub.getCustomer().build();
        when(repository.findById(customer.getId())).thenReturn(Optional.of(customer));
        SearchCustomerUseCase useCase = new SearchCustomerUseCase(repository);
        var result = useCase.execute(customer.getId());
        assertNotNull(result);
        assertEquals(customer.getId(), result.getId());
    }

    @Test
    void execute_notFound() {
        CustomerRepository repository = mock(CustomerRepository.class);
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        SearchCustomerUseCase useCase = new SearchCustomerUseCase(repository);
        assertThrows(NotFoundBusinessException.class, () -> useCase.execute("not-exist-id"));
    }
}