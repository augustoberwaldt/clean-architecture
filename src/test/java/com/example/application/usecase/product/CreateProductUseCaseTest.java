package com.example.application.usecase.product;

import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import com.example.stub.ProductStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseTest {

    @Test
    void execute() {

        ProductRepository repository = mock(ProductRepository.class);
        Product product = ProductStub.getProduct().build();
        when(repository.save(product)).thenReturn(product);
        CreateProductUseCase useCase = new CreateProductUseCase(repository);
        Product result = useCase.execute(product);
        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getValue(), result.getValue());
        verify(repository).save(product);
    }
}