package com.example.txnservice.service;

import com.example.txnservice.model.Transaction;
import com.example.txnservice.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction save(Transaction t) {
        return repo.save(t);
    }

    public List<Transaction> getByUserAndDate(Long userId, LocalDate from, LocalDate to) {
        return repo.findByUserIdAndDateBetween(userId, from, to);
    }
}
