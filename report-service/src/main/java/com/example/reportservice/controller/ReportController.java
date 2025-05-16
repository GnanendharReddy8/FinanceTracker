package com.example.reportservice.controller;

import com.example.reportservice.client.TransactionClient;
import com.example.reportservice.model.Transaction;
import com.example.reportservice.service.ExcelReportService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final TransactionClient txnClient;
    private final ExcelReportService excelService;

    public ReportController(TransactionClient txnClient, ExcelReportService excelService) {
        this.txnClient = txnClient;
        this.excelService = excelService;
    }

    @GetMapping("/summary")
    public ResponseEntity<ByteArrayResource> downloadExcel(
        @RequestParam Long userId,
        @RequestParam String from,
        @RequestParam String to
    ) throws IOException {
        List<Transaction> txns = txnClient.getTransactions(userId, from, to);
        ByteArrayInputStream in = excelService.generate(txns);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(in.readAllBytes()));
    }
}
