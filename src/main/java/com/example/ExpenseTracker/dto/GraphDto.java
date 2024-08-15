package com.example.ExpenseTracker.dto;

import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDto {

    private List<Expense> expenseList;

    private List<Income> incomeList;
}
