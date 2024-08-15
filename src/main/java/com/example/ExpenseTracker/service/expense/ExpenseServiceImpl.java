package com.example.ExpenseTracker.service.expense;

import com.example.ExpenseTracker.dto.ExpenseDto;
import com.example.ExpenseTracker.entity.Expense;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Override
    public Expense postExpense(ExpenseDto expenseDto) {
        return saveOrUpdateExpense(new Expense(), expenseDto);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll().stream()//gets all the expenses and turns them into a stream to work with
                .sorted(Comparator.comparing(Expense::getExpenseDate).reversed()) //we sort the expenses by date in descending order
                .collect(Collectors.toList());//we collect the sorted expenses into a list
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id); //gets expense by id

        if(expense.isPresent()){ //if the expense exists
            return expense.get(); //return the expense
        } else {
            throw new EntityNotFoundException("Expense not found with id: " + id); //else throw an exception
        }
    }

    @Override
    public Expense updateExpense(Long id, ExpenseDto expenseDto) {
        Optional<Expense> expense = expenseRepository.findById(id); //gets

        if(expense.isPresent()){ //if the expense exists
            return saveOrUpdateExpense(expense.get(), expenseDto); //save or update the expense
        } else {
            throw new EntityNotFoundException("Expense not found with id: " + id); //else throw an exception
        }
    }

    @Override
    public void deleteExpense(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id); //gets expense by id

        if(expense.isPresent()){ //if the expense exists
            expenseRepository.delete(expense.get()); //delete the expense
        } else {
            throw new EntityNotFoundException("Expense not found with id: " + id); //else throw an exception
        }
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDto expenseDto) {
        //sets the values of the expense object to the values of the expenseDto object
        expense.setTitle(expenseDto.getTitle());
        expense.setDescription(expenseDto.getDescription());
        expense.setCategory(expenseDto.getCategory());
        expense.setAmount(expenseDto.getAmount());
        expense.setExpenseDate(expenseDto.getExpenseDate());

        return expenseRepository.save(expense); //saves the expense to the database
    }
}
