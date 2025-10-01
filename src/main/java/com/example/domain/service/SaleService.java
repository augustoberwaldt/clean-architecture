package com.example.domain.service;

import com.example.domain.exception.BusinessException;
import com.example.domain.model.Sale;

public class SaleService {

    public void validate(Sale sale) {

        if (sale.getItems() == null || sale.getItems().isEmpty()) {
            throw new BusinessException("A venda deve conter ao menos um item.");
        }

    }
}
