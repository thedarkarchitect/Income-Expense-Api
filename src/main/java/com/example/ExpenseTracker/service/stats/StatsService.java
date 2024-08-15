package com.example.ExpenseTracker.service.stats;

import com.example.ExpenseTracker.dto.GraphDto;
import com.example.ExpenseTracker.dto.StatsDto;

public interface StatsService {
    GraphDto getChartData(); //will return a GraphDto object containing all expenses and incomes depending on date

    StatsDto getStats(); //will return a StatsDto object containing the total income, total expense, latest income, and latest expense
}
