package dev.rwamasirabo.entities;

public class Expense {
    private int expenseId;
    private int emplid;
    private double amount;
    private String dateReq;
    private String status;

    public Expense() {
    }

    public Expense(int expenseId, int emplid, double amount, String dateReq, String status) {
        this.expenseId = expenseId;
        this.emplid = emplid;
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

    public int getEmplid() {
        return emplid;
    }

    public void setEmplid(int emplid) {
        this.emplid = emplid;
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

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", emplid=" + emplid +
                ", amount=" + amount +
                ", dateReq='" + dateReq + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}