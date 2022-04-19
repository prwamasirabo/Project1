package dev.rwamasirabo.entities;

public class Expense {
    private int expenseId;
    private int emplId;
    private double amount;
    private String dateReq;
    private String status;

    public Expense() {
    }

    public Expense(int expenseId, int emplId, double amount, String dateReq, String status) {
        this.expenseId = expenseId;
        this.emplId = emplId;
        this.amount = amount;
        this.dateReq = dateReq;
        this.status = status;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getEmplId() {
        return emplId;
    }

    public void setEmplId(int emplId) {
        this.emplId = emplId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateReq() {
        return dateReq;
    }

    public void setDateReq(String dateReq) {
        this.dateReq = dateReq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}