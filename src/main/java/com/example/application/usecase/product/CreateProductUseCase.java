package com.example.application.usecase.product;

import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateProductUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductUseCase.class);
    private final ProductRepository repository;

    public CreateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(Product product) {
        LOGGER.info("Creating product: {}", product);
        return repository.save(product);
    }
}
