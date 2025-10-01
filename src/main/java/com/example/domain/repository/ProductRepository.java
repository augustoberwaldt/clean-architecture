package com.example.domain.repository;

import com.example.domain.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(String id);

    Slice<Product> findAll(Pageable pageable);
}

