package dev.rwamasirabo.services;
import dev.rwamasirabo.entities.Employee;
import java.util.List;

public interface EmployeeService {
    //    POST /employees
    Employee addEmployee(Employee employee);

    //    GET /employees
    List<Employee> getEmployees();

    //    GET /employees/120
    Employee getEmployee();

    //    PUT /employees/150
    Employee updateEmployee(int empl_id);

    //    DELETE /employees/190
    //void deleteEmployee(Integer employeeId);

    Employee deleteEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(int empl_id);
}

