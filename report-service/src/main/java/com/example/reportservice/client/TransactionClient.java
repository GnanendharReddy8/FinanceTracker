package com.example.reportservice.client;

import com.example.reportservice.model.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "txn-service", url = "${txn.service.url}")
public interface TransactionClient {
    @GetMapping("/txns/get-tr")
    List<Transaction> getTransactions(
        @RequestParam Long userId,
        @RequestParam String from,
        @RequestParam String to
    );
}
