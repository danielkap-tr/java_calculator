package com.example.calculator.api.repository;

import com.example.calculator.api.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA Repository.
 * Demonstrates:
 * - @Repository: Component scan finds this as a data access bean.
 * - JpaRepository: Automatic implementation of standard CRUD.
 */
@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
}
