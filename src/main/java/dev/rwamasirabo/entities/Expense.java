package dev.rwamasirabo.entities;

public class Expense {
    private int expense_id;
    private int empl_id;
    private double amount_used;
    private String DateReq;

    public Expense (){

    }

    public Expense(int expense_id, int empl_id, double amount_used, String dateReq) {
        this.expense_id = expense_id;
        this.empl_id = empl_id;
        this.amount_used = amount_used;
        DateReq = dateReq;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public int getEmpl_id() {
        return empl_id;
    }

    public void setEmpl_id(int empl_id) {
        this.empl_id = empl_id;
    }

    public double getAmount_used() {
        return amount_used;
    }

    public void setAmount_used(double amount_used) {
        this.amount_used = amount_used;
    }

    public String getDateReq() {
        return DateReq;
    }

    public void setDateReq(String dateReq) {
        DateReq = dateReq;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expense_id=" + expense_id +
                ", empl_id=" + empl_id +
                ", amount_used=" + amount_used +
                ", DateReq='" + DateReq + '\'' +
                '}';
    }
}
