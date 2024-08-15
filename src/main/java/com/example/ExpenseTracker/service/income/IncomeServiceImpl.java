package com.example.ExpenseTracker.service.income;

import com.example.ExpenseTracker.dto.IncomeDto;
import com.example.ExpenseTracker.entity.Income;
import com.example.ExpenseTracker.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Override
    public Income postIncome(IncomeDto incomeDto) {
        return saveOrUpdateIncome(new Income(), incomeDto);
    }

    @Override
    public List<IncomeDto> getAllIncomes() {
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getIncomeDate).reversed()) //we sort the incomes by date in descending order
                .map(Income::getIncomeDto) //we map the incomes to their DTOs
                .collect(Collectors.toList());
    }


    private Income saveOrUpdateIncome(Income income, IncomeDto incomeDto) {
        income.setTitle(incomeDto.getTitle());
        income.setAmount(incomeDto.getAmount());
        income.setIncomeDate(incomeDto.getIncomeDate());
        income.setCategory(incomeDto.getCategory());
        income.setDescription(incomeDto.getDescription());

        return incomeRepository.save(income);
    }
}
