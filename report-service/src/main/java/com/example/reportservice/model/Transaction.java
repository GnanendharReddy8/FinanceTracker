package com.example.reportservice.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Transaction {
    private Long id;
    private Long userId;
    private LocalDate date;
    private String category;
    private Double amount;

}
