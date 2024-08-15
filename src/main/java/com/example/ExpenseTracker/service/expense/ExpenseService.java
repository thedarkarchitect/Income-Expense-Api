package com.example.ExpenseTracker.service.expense;

import com.example.ExpenseTracker.dto.ExpenseDto;
import com.example.ExpenseTracker.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense postExpense(ExpenseDto expenseDto); //will save and update expenses using a helper function in the implementation class
    List<Expense> getAllExpenses(); //will return all expenses

    Expense getExpenseById(Long id); //will return an expense by id

    Expense updateExpense(Long id, ExpenseDto expenseDto); //will update an expense by id

    void deleteExpense(Long id); //will delete an expense by id
}

