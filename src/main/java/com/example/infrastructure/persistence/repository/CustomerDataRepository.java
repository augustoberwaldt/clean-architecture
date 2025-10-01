package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerDataRepository extends CassandraRepository<CustomerEntity, UUID> {
}
