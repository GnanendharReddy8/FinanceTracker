package com.example.reportservice.service;

import com.example.reportservice.model.Transaction;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelReportService {
    public ByteArrayInputStream generate(List<Transaction> txns) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Summary");
            String[] headers = {"Date", "Category", "Amount", "Balance"};

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            double balance = 0;
            int rowNum = 1;
            for (Transaction t : txns) {
                Row row = sheet.createRow(rowNum++);
                balance += t.getAmount();

                row.createCell(0).setCellValue(t.getDate().toString());
                row.createCell(1).setCellValue(t.getCategory());
                row.createCell(2).setCellValue(t.getAmount());
                row.createCell(3).setCellValue(balance);
            }

            for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
