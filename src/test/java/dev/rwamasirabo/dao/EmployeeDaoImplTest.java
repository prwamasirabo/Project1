package dev.rwamasirabo.dao;

import dev.rwamasirabo.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoImplTest {

    static EmployeeDAO employeeDAO= new EmployeeDaoImpl();
    static Employee testEmployee = null;

    @Test
    void addEmployee() {
        Employee john = new Employee(0,"patrick","smaith");
        Employee savedEmployee  = employeeDAO.addEmployee(john);
        EmployeeDaoImplTest.testEmployee = savedEmployee;// I have a book I can use in other tests
        Assertions.assertNotEquals(0,savedEmployee.getEmplid());
    }

    @Test
    void getEmployees() {
    }

    @Test
    void getEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}