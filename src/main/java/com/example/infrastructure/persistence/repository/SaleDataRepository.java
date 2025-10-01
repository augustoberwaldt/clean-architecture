package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.SaleEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface SaleDataRepository extends CassandraRepository<SaleEntity, UUID> {
}
