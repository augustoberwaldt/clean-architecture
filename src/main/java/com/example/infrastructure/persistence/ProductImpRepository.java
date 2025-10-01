package com.example.infrastructure.persistence;

import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import com.example.infrastructure.persistence.converter.ProductConverter;
import com.example.infrastructure.persistence.entity.ProductEntity;
import com.example.infrastructure.persistence.repository.ProductDataRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProductImpRepository implements ProductRepository {

    private final ProductDataRepository repository;
    private final ProductConverter productConverter;

    public ProductImpRepository(ProductDataRepository productDataRepository,
                                ProductConverter productConverter) {
        this.repository = productDataRepository;
        this.productConverter = productConverter;
    }

    @Transactional
    @Override
    public Product save(Product product) {

        var entity = productConverter.convert(product);
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        var saved = repository.save(entity);
        return productConverter.convert(saved);
    }

    @Override
    public Optional<Product> findById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return repository.findById(uuid).map(productConverter::convert);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Slice<Product> findAll(Pageable pageable) {
        Slice<ProductEntity> entities = this.repository.findAll(pageable);
        return entities.map(productConverter::convert);
    }
}
