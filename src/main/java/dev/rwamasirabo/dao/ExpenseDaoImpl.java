package dev.rwamasirabo.dao;

import dev.rwamasirabo.entities.Expense;
import dev.rwamasirabo.exceptions.ResourceNotFound;
import dev.rwamasirabo.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dev.rwamasirabo.utilities.Statuses.*;

public class ExpenseDaoImpl implements ExpenseDAO{

    public static final String insert_expense  = "" +
            "insert into expense values\n" +
            "(default,?, ?,current_timestamp,?);\n";

    public static final String get_expense = "" +
            "select * from expense;\n";

    public static final String get_expense_by_id = "select expense_id,empl_id, amount, datereq, status)\n" +
            " from where expense where expense_id =?\n";

    public static final String update_expense = " "+
            "update expense set expense_id =? where expense_id= ?;\n";

    public static final String delete_expense = ""+
            "delete  from expense where expense_id=? \n";

    public static final String approve_expense = "" +
            "update expense set status='?' where expense_id = ? \n";

    public static final String deny_expense = "" +
            "update expense \n" +
            "status = ? \n" +
            "where expense_id = ? \n";

    @Override
    public boolean addExpense(Expense expense) {
        boolean returnValue = false;
        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(insert_expense, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,expense.getEmplid());
            ps.setDouble(2,expense.getAmount());
            ps.setString(3,pendingStatus );
            ps.execute();
            ResultSet rs = ps.executeQuery();

            //if (rs.getRow()== 0) {
            //  throw new ResourceNotFound(employeeId.intValue());
            // }

            if (rs.next()){
                returnValue = true;
            } // move to first record



        } catch (SQLException e) {
            e.printStackTrace();
            //Logger.log(e.getMessage(), LogLevel.ERROR);
        }

        return returnValue;
    }
    @Override
    public List<Expense> getExpenses() {
        List<Expense> expenses = new ArrayList();
        try {
            Connection conn = ConnectionUtil.getConnection();
            String sql = "select * from expense";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setEmplid(rs.getInt("empl_id"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setStatus(rs.getString("status"));
                expenses.add(expense);
            }



        } catch (SQLException e) {
            e.printStackTrace();

        }
        return expenses;
    }

    @Override
    public List<Expense> getExpenseByStatus(String status) {
      List<Expense> expenses = new ArrayList();
        try {
        Connection conn = ConnectionUtil.getConnection();
        String sql = "select * from expense";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        while (rs.next()) {
            Expense expense = new Expense();
            expense.setExpenseId((rs.getInt("expense_id")));
            expense.setEmplid(rs.getInt("empl_id"));
            expense.setAmount(rs.getDouble("amount"));
            expenses.add(expense);
        }



    } catch (
    SQLException e) {
        e.printStackTrace();
        //Logger.log(e.getMessage(), LogLevel.ERROR);
        //return null;
    }
        return expenses;
}

    @Override
    public Expense getExpense(Integer expenseId) {
        Expense expense = new Expense();
        try {
            Connection conn = ConnectionUtil.getConnection();
            String sql = "select * from expense where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expenseId.intValue());

            ResultSet rs = ps.executeQuery();

                 if (rs.next()){
                expense.setExpenseId(rs.getInt(" expense_id"));
                expense.setEmplid(rs.getInt("empl_id"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setStatus(rs.getString("status"));
            } // move to first record

            else {
                throw new ResourceNotFound(expenseId.intValue());
            }

            //returnValue = false;

        } catch (SQLException e) {
            e.printStackTrace();
            //Logger.log(e.getMessage(), LogLevel.ERROR);
        }
        return expense;
    }
       @Override
    public boolean updateExpense(Expense expense) {

        boolean successUpdate = false;
        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(update_expense, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1,expense.getAmount());
            ps.setString(2,expense.getStatus());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                successUpdate = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return successUpdate;
    }

    @Override
    public boolean deleteExpense(Integer expenseId) {
        boolean deleted= false;
        try {
            Connection conn = ConnectionUtil.getConnection();
            String sql = "delete from expense where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expenseId.intValue());
            int rowsUpdated = ps.executeUpdate();

            if(rowsUpdated == 0){
                throw new ResourceNotFound(expenseId.intValue());
            } else {
                deleted = true ;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return deleted;

    }

    @Override
    public List<Expense> getExpensesByEmployeeId(Integer employeeId) {
        final List<Expense> employeeIdExpenseList = new ArrayList<>();
        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("");

            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Expense expense = buildExpenseFromResultSet(rs);
                employeeIdExpenseList.add(expense);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    return employeeIdExpenseList;
    }

public static Expense buildExpenseFromResultSet(ResultSet rs) throws SQLException {
        Expense expense = new Expense();
        if (rs != null){
            Integer expenseId = rs.getInt("expense_id");
            Integer employeeId = rs.getInt("empl_id");
            Double amount = rs.getDouble("amount");
            Long datereq = rs.getTimestamp("datereq").getTime();
            String status = rs.getString("status");

            expense.setExpenseId(expenseId);
            expense.setEmplid(employeeId);
            expense.setAmount(amount);
            expense.setDateReq(datereq);
            expense.setStatus(status);


        }
            return expense;
        }
    @Override
    public boolean approveExpense(int expenseId) {
        boolean returnValue = false;

        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(approve_expense, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, approvedStatus);
            ps.setInt(2, expenseId);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys(); //ResultSet a virtual table of results

            if (rs.next()) {
                returnValue = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    @Override
    public boolean denyExpense(int expenseId) {
        boolean returnValue = false;

        try {
            Connection conn = ConnectionUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(deny_expense, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, deniedStatus);
            ps.setInt(2, expenseId);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys(); //ResultSet a virtual table of results

            if (rs.next()) {
                returnValue = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }
    }