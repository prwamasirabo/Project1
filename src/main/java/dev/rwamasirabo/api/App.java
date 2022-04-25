package dev.rwamasirabo.api;

import com.google.gson.Gson;
import dev.rwamasirabo.dao.EmployeeDaoImpl;
import dev.rwamasirabo.entities.Employee;
import dev.rwamasirabo.exceptions.ResourceNotFound;
import dev.rwamasirabo.services.*;
import dev.rwamasirabo.services.EmployeeService;
import dev.rwamasirabo.services.EmployeeServiceImpl;
import io.javalin.Javalin;

import java.util.List;

public class App {
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());
    public static Gson gson = new Gson();

    public static void main(String[] args) {

        //ExpenseService expenseService = new ExpenseServiceImpl();
        //EmployeeService employeeService = new EmployeeServiceImpl();

        Javalin javalinApp = Javalin.create();

        javalinApp.get("/", context -> {
            context.result("Welcome");
            context.status(200);
        });

        // POST /employees  (create)
        javalinApp.post("/employees", context -> {

            String body = context.body();
            Gson gson = new Gson();

            Employee employee = gson.fromJson(body, Employee.class);
            employeeService.addEmployee(employee);
            context.status(201);
            context.result("Added employee!!!");

        });

        //READ
        javalinApp.get("/employees", context -> {

                List<Employee> employees = employeeService.getEmployees();
               String employeeJSON = gson.toJson(employees);
                context.result(employeeJSON);
        });


        // POST /employees/120
        javalinApp.get("/employees/{empl_id}", context -> {
            int empl_id = Integer.parseInt(context.pathParam("empl_id"));

            try {
                String employeeJSON =  gson.toJson(employeeService.updateEmployee(empl_id));
                context.result(employeeJSON);
            }catch (ResourceNotFound e){
                context.status(404);
                context.result("The employee id " + empl_id + "was not found");
            }

        });
       // UPDATE PUT /employees/150
        javalinApp.put("/employees/{empl_id}", context -> {
            int id = Integer.parseInt(context.pathParam("empl_id"));
            String body = context.body();
            Employee employee = gson.fromJson(body,Employee.class);
            employee.setEmplid(employee.getEmplid());// the id in the uri should take precedence
            employeeService.updateEmployee(employee.getEmplid());
            context.result("Employee replaced");
        });
        // Delete /employees/150
        javalinApp.delete("/employees/{empl_id}", context -> {
            int empl_id = Integer.parseInt(context.pathParam("empl_id"));
            boolean result = employeeService.deleteEmployee(empl_id);
            if(result){
                context.status(204);
                context.result("Employee deleted");
            }else{
                context.status(404);
                context.result("Employee not found");
            }


        });
        javalinApp.start(5000);

    }

}

