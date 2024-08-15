package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.dto.IncomeDto;
import com.example.ExpenseTracker.entity.Income;
import com.example.ExpenseTracker.service.income.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/income")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<?> postIncome(@RequestBody IncomeDto incomeDto) {
        Income createdIncome = incomeService.postIncome(incomeDto);

        if(createdIncome != null) { //if it exists
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIncomes() {
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }


}
