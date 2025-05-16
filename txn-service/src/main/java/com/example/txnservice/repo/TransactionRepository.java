package com.example.txnservice.repo;

import com.example.txnservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to);
}
