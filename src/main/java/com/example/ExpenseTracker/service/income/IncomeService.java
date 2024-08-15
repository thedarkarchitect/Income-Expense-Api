package com.example.ExpenseTracker.service.income;

import com.example.ExpenseTracker.dto.IncomeDto;
import com.example.ExpenseTracker.entity.Income;

import java.util.List;

public interface IncomeService {
    Income postIncome(IncomeDto incomeDto); //will save and update incomes using a helper function in the implementation class

    List<IncomeDto> getAllIncomes(); //will return all incomes

//    Income getIncomeById(Long id); //will return an income by id
//
//    Income updateIncome(Long id, IncomeDto incomeDto); //will update an income by id
//
//    void deleteIncome(Long id); //will delete an income by id
}
