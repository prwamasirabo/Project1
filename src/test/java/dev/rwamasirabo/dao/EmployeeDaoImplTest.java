package dev.rwamasirabo.dao;

import dev.rwamasirabo.entities.Employee;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeDaoImplTest {

    static EmployeeDAO employeeDAO= new EmployeeDaoImpl();
    static Employee testEmployee = null;

    @Test
    @Order(1)
    void addEmployee() {
        Employee john = new Employee(0,"Daniella","Janet");
        Employee savedEmployee  = employeeDAO.addEmployee(john);
        EmployeeDaoImplTest.testEmployee = savedEmployee;// I have a book I can use in other tests
        Assertions.assertNotEquals(0,savedEmployee.getEmplid());
    }

    @Test
    @Order(2)
    void getEmployees() {
        Employee a = new Employee(0,"A","B");
        Employee b = new Employee(0,"A","B");
        Employee c = new Employee(0,"A","B");
        employeeDAO.addEmployee(a);
        employeeDAO.addEmployee(b);
        employeeDAO.addEmployee(c);

        List<Employee> newEmployeeList = employeeDAO.getEmployees();

        Assertions.assertTrue(newEmployeeList != null  && !newEmployeeList.isEmpty());
    }

    @Test
    @Order(3)
    void updateEmployee() {
        EmployeeDaoImplTest.testEmployee.setFirstName("patrick");
        employeeDAO.updateEmployee(testEmployee);
        Employee retrievedEmployee = employeeDAO.getEmployee(testEmployee.getEmplid());
        Assertions.assertEquals("George", retrievedEmployee.getFirstName());
    }

    @Test
    @Order(4)
    void deleteEmployee() {
        boolean result = employeeDAO.deleteEmployee(testEmployee.getEmplid());
        Assertions.assertTrue(result);
    }
}