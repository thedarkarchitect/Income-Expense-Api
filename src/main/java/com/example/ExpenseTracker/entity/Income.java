package com.example.ExpenseTracker.entity;

import com.example.ExpenseTracker.dto.IncomeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String category;

    private Integer amount;

    private LocalDate incomeDate;

    public IncomeDto getIncomeDto() {
        IncomeDto incomeDto = new IncomeDto();

        incomeDto.setId(this.id);
        incomeDto.setTitle(this.title);
        incomeDto.setDescription(this.description);
        incomeDto.setCategory(this.category);
        incomeDto.setAmount(this.amount);
        incomeDto.setIncomeDate(this.incomeDate);

        return incomeDto;
    }
}
