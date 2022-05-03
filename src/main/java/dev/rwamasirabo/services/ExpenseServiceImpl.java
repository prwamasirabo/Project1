package dev.rwamasirabo.services;

import dev.rwamasirabo.dao.ExpenseDAO;
import dev.rwamasirabo.dao.ExpenseDaoImpl;
import dev.rwamasirabo.entities.Expense;
import dev.rwamasirabo.utilities.Statuses;

import java.util.List;
public class ExpenseServiceImpl implements ExpenseService {

    public ExpenseDAO expenseDAO;

    //Constructor for expenseDao
    public ExpenseServiceImpl (ExpenseDaoImpl expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @Override
    public boolean addExpense(Expense expense) {
        return this.expenseDAO.addExpense(expense);
    }

    @Override
    public List<Expense> getExpenseByStatus(String status) {
        return this.expenseDAO.getExpenseByStatus(status);
    }

    @Override
    public List<Expense> getExpense() {
        return this.expenseDAO.getExpenses();
    }

    @Override
    public Expense getExpense(Integer expenseId) {
        return this.expenseDAO.getExpense(expenseId);
    }

    @Override
    public boolean updateExpense(Expense expense) {
        return this.expenseDAO.updateExpense(expense);
    }


    @Override
    public boolean approveExpense(Integer expenseId) {

        Expense expense = this.expenseDAO.getExpense(expenseId);
        expense.setStatus(Statuses.approvedStatus);
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public boolean denyExpense(Integer expenseId) {
        Expense expense = this.expenseDAO.getExpense(expenseId);
        expense.setStatus(Statuses.deniedStatus);
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public boolean deleteExpense(Integer expenseId) {
        return this.expenseDAO.deleteExpense(expenseId);
    }


    @Override
    public List<Expense> getExpensesByEmployeeId(Integer employeeId) {
        return this.expenseDAO.getExpensesByEmployeeId(employeeId);
    }

}

