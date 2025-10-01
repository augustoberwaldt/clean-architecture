package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ProductDataRepository extends CassandraRepository<ProductEntity, UUID> {
}
