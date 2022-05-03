package dev.rwamasirabo.api;

import com.google.gson.Gson;
import dev.rwamasirabo.dao.EmployeeDaoImpl;
import dev.rwamasirabo.dao.ExpenseDaoImpl;
import dev.rwamasirabo.entities.Employee;
import dev.rwamasirabo.entities.Expense;
import dev.rwamasirabo.exceptions.ResourceNotFound;
import dev.rwamasirabo.services.*;
import dev.rwamasirabo.services.EmployeeService;
import dev.rwamasirabo.services.EmployeeServiceImpl;
import dev.rwamasirabo.utilities.Statuses;
import io.javalin.Javalin;

import java.util.List;

public class App {
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoImpl());
    public static Gson gson = new Gson();

    public static void main(String[] args) {
        String connectionString = System.getenv("DB_CONNECTION");

        System.out.println(connectionString);
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
                context.status(201);
                context.result("Employee deleted");
            }else{
                context.status(404);
                context.result("Employee not found");
            }


        });
         //POST/ expense (create)
        javalinApp.post("/expenses", context -> {

            String body = context.body();
            Gson gson = new Gson();

            Expense expense = gson.fromJson(body, Expense.class);
            expenseService.addExpense(expense);
            context.status(201);
            context.result("Expense created !!!");
        });

        javalinApp.get("/expenses/{empl_id}", context -> {
           int empl_id = Integer.parseInt(context.pathParam("empl_id"));

            try {
               String expenseJSON =  gson.toJson(expenseService.getExpense());
                context.result(expenseJSON );
            }catch (ResourceNotFound e){
                context.status(404);
                context.result("The Expense id " + empl_id + "was not found");
            }

        });

        //Get /Expenses
        javalinApp.get("/expenses", context -> {
                Gson gson = new Gson();
            List<Expense> expenseList = expenseService.getExpense();
            String expenseJSON = gson.toJson(expenseList);
            context.status(201);
            context.result(expenseJSON);
       });
        // UPDATE PUT /expenses/150
        javalinApp.put("/expenses/{expense_id}", context -> {
            int id = Integer.parseInt(context.pathParam("expense_id"));
            String body = context.body();
            Expense expense = gson.fromJson(body,Expense.class);
            expense.setExpenseId(expense.getExpenseId());// the id in the uri should take precedence
            expenseService.updateExpense(expense);
            context.result("Expense updated");
        });

        //PATCH /expenses/20/approve
//returns a 404 if expense not found
        javalinApp.patch("/expenses/{id}/approve", context -> {
            Gson gson = new Gson();

            try {
                int expenseId = Integer.valueOf(context.pathParam("id"));
                String body = context.body();
                Expense expense = gson.fromJson(body, Expense.class);
                expenseService.approveExpense(expenseId);
                context.status(201);
                context.result("Approved expense!!!");
            }catch(Exception e){
                context.status(404);
                context.result("Expense not approved");
            }
        });

// PATCH /expenses/20/deny
//returns a 404 if expense not found
        javalinApp.patch("/expenses/{id}/denied", context -> {

            Gson gson = new Gson();

            try {
                int expenseId = Integer.valueOf(context.pathParam("id"));
                String body = context.body();
                Expense expense = gson.fromJson(body, Expense.class);
                expenseService.denyExpense(expenseId);
                context.status(201);
                context.result("Denied expense!!!");
            }catch(Exception e){
                context.status(201);
                context.result("Denied not approved");
            }
        });
        // Delete /expenses/150
        javalinApp.delete("/expenses/{expense_id}", context -> {
            int expense_id = Integer.parseInt(context.pathParam("expense_id"));
            boolean result = expenseService.deleteExpense(expense_id);
            if(result){
                context.status(201);
                context.result("Expense deleted");
            }else{
                context.status(404);
                context.result("Expense not found");
            }

        });
        javalinApp.start(5001);

    }

}

