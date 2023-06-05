package org.vicary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vicary.model.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
