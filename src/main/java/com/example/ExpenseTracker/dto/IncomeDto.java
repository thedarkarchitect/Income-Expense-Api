package com.example.ExpenseTracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Integer amount;
    private LocalDate incomeDate;

}
