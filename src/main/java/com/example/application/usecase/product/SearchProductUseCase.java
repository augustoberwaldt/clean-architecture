package com.example.application.usecase.product;

import com.example.domain.exception.NotFoundBusinessException;
import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchProductUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchProductUseCase.class);

    private final ProductRepository repository;

    private static final String PRODUCT_NOT_FOUND = "Produto não encontrado.";

    public SearchProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(String id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new NotFoundBusinessException(PRODUCT_NOT_FOUND));
        LOGGER.info("SearchProductUseCase.execute - [{}]", product.getId());
        return product;
    }
}

