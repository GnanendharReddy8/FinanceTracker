package com.example.txnservice.repo;

import com.example.txnservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdAndDateBetween(Long userId, LocalDate from, LocalDate to);
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByCategory(String category);

}
