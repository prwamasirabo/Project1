package dev.rwamasirabo.dao;

import dev.rwamasirabo.entities.Expense;

import java.util.List;

public interface ExpenseDAO {
    //    POST /expenses
    boolean addExpense(Expense expense);

    //    GET /expenses?status=pending
    List<Expense> getExpenseByStatus(String status);

    //    GET /expenses/12
    Expense getExpense(Integer expenseId);

    //    PUT /expenses/15
    boolean updateExpense(Expense expense);


    //    DELETE/expenses/19
    boolean deleteExpense(Integer expenseId);

    //    GET /employees/120/expenses
    List<Expense> getExpensesByEmployeeId(Integer employeeId);

    //    POST /employees/120/expenses
    boolean addExpenseByEmployeeId(Expense expense, Integer employeeId);

}

