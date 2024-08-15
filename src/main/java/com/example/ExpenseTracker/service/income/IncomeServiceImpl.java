package com.example.ExpenseTracker.service.income;

import com.example.ExpenseTracker.dto.IncomeDto;
import com.example.ExpenseTracker.entity.Income;
import com.example.ExpenseTracker.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Income getIncomeById(Long id) {
        Optional<Income> income = incomeRepository.findById(id);

        if(income.isPresent()){
            return income.get();//returns the income with id
        } else {
            throw new EntityNotFoundException("Income not found with id: " + id);
        }
    }

    @Override
    public Income updateIncome(Long id, IncomeDto incomeDto) {
        Optional<Income> income = incomeRepository.findById(id);

        if (income.isPresent()) {
            return saveOrUpdateIncome(income.get(), incomeDto);
        } else {
            throw new EntityNotFoundException("Income not found with id: " + id);
        }
    }

    @Override
    public void deleteIncome(Long id) {
        Optional<Income> income = incomeRepository.findById(id);

        if(income.isPresent()){
            incomeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Income not found with id: " + id);
        }
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
