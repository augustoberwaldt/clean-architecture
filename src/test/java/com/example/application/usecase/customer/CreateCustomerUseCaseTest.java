package com.example.application.usecase.customer;

import com.example.domain.model.Customer;
import com.example.domain.repository.CustomerRepository;
import com.example.stub.CustomerStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {

    @Test
    void execute() {
        CustomerRepository repository = mock(CustomerRepository.class);
        CreateCustomerUseCase useCase = new CreateCustomerUseCase(repository);
        Customer input = CustomerStub.getCustomer().build();
        when(repository.save(input)).thenReturn(input);

        Customer result = useCase.execute(input);

        verify(repository).save(input);
        assert result != null;
        assert result.getId().equals(input.getId());
    }
}