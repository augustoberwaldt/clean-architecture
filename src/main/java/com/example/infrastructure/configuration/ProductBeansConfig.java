package com.example.infrastructure.configuration;

import com.example.application.usecase.product.CreateProductUseCase;
import com.example.application.usecase.product.ListProductUseCase;
import com.example.application.usecase.product.SearchProductUseCase;
import com.example.application.usecase.product.UpdateProductUseCase;
import com.example.infrastructure.persistence.ProductImpRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeansConfig {

    @Bean
    public CreateProductUseCase createProductUseCase(ProductImpRepository repository) {
        return new CreateProductUseCase(repository);
    }

    @Bean
    public SearchProductUseCase searchProductUseCase(ProductImpRepository repository) {
        return new SearchProductUseCase(repository);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductImpRepository repository) {
        return new UpdateProductUseCase(repository);
    }

    @Bean
    public ListProductUseCase listProductUseCase(ProductImpRepository repository) {
        return new ListProductUseCase(repository);
    }
}