package dev.rwamasirabo.services;

import dev.rwamasirabo.dao.EmployeeDAO;
import dev.rwamasirabo.entities.Employee;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO =employeeDAO;}

    @Override
    public Employee addEmployee(Employee employee) {
        return this.employeeDAO.addEmployee(employee);

    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeDAO.getEmployees();
    }

    @Override
    public Employee getEmployee() {
        return null;
    }

    @Override
    public Employee updateEmployee(int emplid) {
        return null;
    }

    @Override
    public Employee deleteEmployee(Employee employee) {
        return null;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return this.employeeDAO.updateEmployee(employee);

    }

    @Override
    public boolean deleteEmployee(int emplid) {
        boolean result = this.employeeDAO.deleteEmployee(emplid);
        return result ;
    }
}
