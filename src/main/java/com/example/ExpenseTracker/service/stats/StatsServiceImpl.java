package com.example.ExpenseTracker.service.stats;

import com.example.ExpenseTracker.dto.GraphDto;
import com.example.ExpenseTracker.dto.StatsDto;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.entity.Income;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import com.example.ExpenseTracker.repository.IncomeRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

    private final IncomeRepository incomeRepository;

    private final ExpenseRepository expenseRepository;

    @Override
    public GraphDto getChartDate() {
        LocalDate endDate = LocalDate.now(); //we get the current date
        LocalDate startDate = endDate.minusDays(27); //we get the date 28 days ago

        GraphDto graphDto = new GraphDto();

        graphDto.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
        graphDto.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));

        return graphDto;
    }

    public StatsDto getStats() {
        Double totalIncome = incomeRepository.sumAllAmounts(); //we get the total income
        Double totalExpense = expenseRepository.sumAllAmounts(); // we get the total expense

        Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();  //we get the latest income
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc(); //we get the latest expense

        StatsDto statsDto = new StatsDto(); //we create a new StatsDto object

        statsDto.setIncome(totalIncome); // we set the total income
        statsDto.setExpense(totalExpense); //   we set the total income and total expense

        optionalIncome.ifPresent(statsDto::setLatestIncome); //we set the latest income
        optionalExpense.ifPresent(statsDto::setLatestExpense); //we set the latest expense

        statsDto.setBalance(totalIncome - totalExpense); //we set the balance

        List<Income> incomeList = incomeRepository.findAll(); //we get all incomes
        List<Expense> expenseList = expenseRepository.findAll(); //we get all expenses

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min(); //we calculate the minimum income
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max(); //we calculate the maximum income

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min(); //we calculate the minimum expense
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max(); //we calculate the maximum expense

//        OptionalDouble averageIncome = incomeList.stream().mapToDouble(Income::getAmount).average(); //we calculate the average income
//        OptionalDouble averageExpense = expenseList.stream().mapToDouble(Expense::getAmount).average(); //we calculate the average expense

        statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);//we set the maximum income
        statsDto.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);//we set the minimum income

        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);//we set the maximum expense
        statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);//we set the minimum expense

        return statsDto;
    }
}
