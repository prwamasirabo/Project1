package dev.rwamasirabo.dao;

import dev.rwamasirabo.entities.Expense;

import java.util.List;

public class ExpenseDaoImpl implements ExpenseDAO{




    @Override
    public boolean addExpense(Expense expense) {
        return false;
    }

    @Override
    public List<Expense> getExpenseByStatus(String status) {
        return null;
    }

    @Override
    public Expense getExpense(Integer expenseId) {
        return null;
    }

    @Override
    public boolean updateExpense(Expense expense) {
        return false;
    }

    @Override
    public boolean deleteExpense(Integer expenseId) {
        return false;
    }

    @Override
    public List<Expense> getExpensesByEmployeeId(Integer employeeId) {
        return null;
    }

    @Override
    public boolean addExpenseByEmployeeId(Expense expense, Integer employeeId) {
        return false;
    }
}
