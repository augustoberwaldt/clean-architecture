package com.example.infrastructure.persistence;

import com.example.domain.model.Sale;
import com.example.domain.repository.SaleRepository;
import com.example.infrastructure.persistence.converter.SaleConverter;
import com.example.infrastructure.persistence.repository.SaleDataRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SaleImpRepository implements SaleRepository {

    private final SaleDataRepository repository;
    private final SaleConverter converter;

    public SaleImpRepository(SaleDataRepository repository,
                             SaleConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Optional<Sale> findById(String id) {

        try {
            UUID uuid = UUID.fromString(id);
            return repository.findById(uuid).map(converter::convert);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Sale save(Sale sale) {

        var entity = converter.convert(sale);
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        var saved = repository.save(entity);
        return converter.convert(saved);
    }
}
