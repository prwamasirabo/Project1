package dev.rwamasirabo.dao;

import dev.rwamasirabo.entities.Employee;

import java.util.List;

public interface EmployeeDAO {

 boolean addEmployee(Employee employee);

    //    GET /employees
    List<Employee> getEmployees();

    //    GET /employees/120
    Employee getEmployee();

    //    PUT /employees/150
    boolean updateEmployee(Employee employee);

    //    DELETE /employees/190
    boolean deleteEmployee(Integer employeeId);
}
