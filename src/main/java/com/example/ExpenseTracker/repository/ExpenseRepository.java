package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByExpenseDateBetween(LocalDate startDate, LocalDate endDate); //will return all incomes between two dates

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double sumAllAmounts();

    //make sure to get the naming convention right to match the entity class
    Optional<Expense> findFirstByOrderByExpenseDateDesc(); //will return the latest expense
}
