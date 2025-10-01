package com.example.infrastructure.configuration;

import com.example.application.usecase.sale.AddItemSaleUseCase;
import com.example.application.usecase.sale.ConsumeSaleItemSaleUseCase;
import com.example.application.usecase.sale.CreateSaleUseCase;
import com.example.application.usecase.sale.SearchSaleUseCase;
import com.example.domain.service.SaleService;
import com.example.infrastructure.messaging.SaleKafkaProducer;
import com.example.infrastructure.persistence.ProductImpRepository;
import com.example.infrastructure.persistence.SaleImpRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleBeansConfig {

    @Bean
    public CreateSaleUseCase createSaleUseCase(SaleImpRepository repository) {
        return new CreateSaleUseCase(repository, new SaleService());
    }

    @Bean
    public SearchSaleUseCase searchSaleUseCase(SaleImpRepository repository) {
        return new SearchSaleUseCase(repository);
    }

    @Bean
    public AddItemSaleUseCase addItemSaleUseCase(SaleKafkaProducer producer) {
        return new AddItemSaleUseCase(producer);
    }

    @Bean
    public ConsumeSaleItemSaleUseCase consumeSaleItemSaleUseCase(SaleImpRepository repository,
                                                                 ProductImpRepository productImpRepository) {

        return new ConsumeSaleItemSaleUseCase(repository, productImpRepository, new SaleService());
    }

}