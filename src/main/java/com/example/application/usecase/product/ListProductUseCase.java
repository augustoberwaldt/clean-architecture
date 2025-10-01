package com.example.application.usecase.product;

import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public class ListProductUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListProductUseCase.class);

    private final ProductRepository repository;

    public ListProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public Slice<Product> execute(Pageable pageable) {

        Slice<Product> products = repository.findAll(pageable);
        LOGGER.info("Total products found: {}", products.getNumberOfElements());
        return products;
    }
}

