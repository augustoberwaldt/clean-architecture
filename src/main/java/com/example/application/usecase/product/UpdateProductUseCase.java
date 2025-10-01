package com.example.application.usecase.product;

import com.example.domain.exception.NotFoundBusinessException;
import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateProductUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProductUseCase.class);

    private final ProductRepository repository;

    private static final String PRODUCT_NOT_FOUND = "Produto não encontrado.";

    public UpdateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(String id, Product product) {

        Product productSaved = repository.findById(id)
                .orElseThrow(() -> new NotFoundBusinessException(PRODUCT_NOT_FOUND));

        product.setId(productSaved.getId());
        repository.save(product);

        LOGGER.info("Produto atualizado com sucesso: {}", product);
        return product;
    }
}

