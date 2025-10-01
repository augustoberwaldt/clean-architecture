package com.example.application.usecase.sale;

import com.example.domain.model.Sale;
import com.example.domain.repository.SaleRepository;
import com.example.domain.service.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateSaleUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSaleUseCase.class);
    private final SaleRepository repository;
    private final SaleService saleService;

    public CreateSaleUseCase(SaleRepository repository, SaleService saleService) {
        this.repository = repository;
        this.saleService = saleService;
    }

    public Sale execute(Sale sale) {
        LOGGER.info("Creating sale: {}", sale);

        saleService.validate(sale);

        return repository.save(sale);
    }
}
