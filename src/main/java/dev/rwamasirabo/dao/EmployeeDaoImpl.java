package dev.rwamasirabo.dao;

import dev.rwamasirabo.entities.Employee;
import dev.rwamasirabo.exceptions.ResourceNotFound;
import dev.rwamasirabo.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {

    public static final String INSERT_EMPLOYEE = "insert into employee (first_name,last_name)\n" +
            "values (?,?)\n";

    public static final String GET_EMPLOYEE = "insert into employee (first_name,last_name)\n" +
            "values (default,?,?)\n";

    @Override
    public Employee addEmployee(Employee employee) {
       // boolean returnValue = false;
         Employee newEmployee = new Employee();
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://rwamasirabo-db.cv1tp2j6wufq.us-east-2.rds.amazonaws.com/project1?user=postgres&password=Widetechchf01!");
            //return conn;
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_EMPLOYEE,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Integer employeeId = resultSet.getInt("empl_id");
                newEmployee.setEmplid(resultSet.getInt("empl_id"));
                //newEmployee.setEmplid(employeeId);
                 //returnValue = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return newEmployee ;
    }


    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList();
        try {
            Connection conn = ConnectionUtil.getConnection();
            String sql = "select * from employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmplid(rs.getInt("empl_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employees.add(employee);
            }



        } catch (SQLException e) {
            e.printStackTrace();
            //Logger.log(e.getMessage(), LogLevel.ERROR);
            //return null;
        }
        return employees;
    }
    @Override
    public Employee getEmployee(Integer employeeId) {
       // boolean returnValue = false;
        Employee employee = new Employee();
        try {
            Connection conn = ConnectionUtil.getConnection();
            String sql = "select * from employee where empl_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId.intValue());

            ResultSet rs = ps.executeQuery();

            //if (rs.getRow()== 0) {
              //  throw new ResourceNotFound(employeeId.intValue());
           // }

            if (rs.next()){
                employee.setEmplid(rs.getInt("empl_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
            } // move to first record

            else {
                throw new ResourceNotFound(employeeId.intValue());
            }

            //returnValue = false;

        } catch (SQLException e) {
            e.printStackTrace();
            //Logger.log(e.getMessage(), LogLevel.ERROR);
                    }
        return employee ;
        }



    @Override
    public boolean updateEmployee(Employee employee) {
            boolean returnValue = false;

            try {
                Connection conn = ConnectionUtil.getConnection();
                String sql = "update employee set first_name = ?, lats_name = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setInt(3, employee.getEmplid());
                int rowsUpdated = ps.executeUpdate();

                if(rowsUpdated == 0){
                    throw new ResourceNotFound(employee.getEmplid());
                } else {
                    returnValue = true ;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                returnValue = false ;
            }
            return returnValue;

    }

    @Override
    public boolean deleteEmployee(Integer employeeId) {
        boolean returnValue = false;
        try {
            Connection conn = ConnectionUtil.getConnection();
            String sql = "delete employee set empl_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId.intValue());
            int rowsUpdated = ps.executeUpdate();

            if(rowsUpdated == 0){
                throw new ResourceNotFound(employeeId.intValue());
            } else {
                returnValue = true ;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            returnValue = false ;
        }
        return returnValue;
    }
}
