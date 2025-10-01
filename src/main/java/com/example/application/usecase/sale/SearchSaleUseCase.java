package com.example.application.usecase.sale;

import com.example.domain.exception.NotFoundBusinessException;
import com.example.domain.model.Sale;
import com.example.domain.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchSaleUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchSaleUseCase.class);
    private final SaleRepository repository;

    private static final String SALE_NOT_FOUND = "Venda não encontrado.";

    public SearchSaleUseCase(SaleRepository repository) {
        this.repository = repository;
    }

    public Sale execute(String id) {

        Sale sale = repository.findById(id)
                .orElseThrow(() -> new NotFoundBusinessException(SALE_NOT_FOUND));

        LOGGER.info("SearchSaleUseCase.execute - [{}]", sale.getId());

        return sale;
    }
}
