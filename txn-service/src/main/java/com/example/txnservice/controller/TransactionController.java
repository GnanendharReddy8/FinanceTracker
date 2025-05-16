package com.example.txnservice.controller;

import com.example.txnservice.model.Transaction;
import com.example.txnservice.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/txns")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Transaction> add(@RequestBody Transaction t) {
        return ResponseEntity.ok(service.save(t));
    }

    @GetMapping("/get-tr")
    public ResponseEntity<List<Transaction>> list(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return ResponseEntity.ok(service.getByUserAndDate(userId, from, to));
    }
}
