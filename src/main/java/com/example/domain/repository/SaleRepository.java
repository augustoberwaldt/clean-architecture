package com.example.domain.repository;

import com.example.domain.model.Sale;
import java.util.Optional;

public interface SaleRepository {

    Optional<Sale> findById(String id);
    Sale save(Sale sale);
}
