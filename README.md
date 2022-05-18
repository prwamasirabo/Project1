# ** PROJECT ONE
---
You will be creating a REST API for an expense reimbursement system. The system will will allow the company to track expenses and analyze spending. You will design the entities. You have two entities. Employee and Expense.


## Expense Reimbursement API
---
The Employee Reimbursement System (ERS) is REST API that helps manage the process of reimbursing employees for expenses. Employees can be created and edited via the API. Expenses for employees can be added and updated to pending and approved. Reviewed expenses can not be edited.

## Roles and Responsabilities
---

Created two entitie and implemented the following routes
- GET /employees/120/expenses
* Returns expenses for employee 120
* POST /employees/120/expenses
* Adds an expense to employee 120

## Technologies used 
---

 - Java  
* Postgres 
* log4j or cusomt logger for logging were used 
*  Javalin was used for the API layer
*  Testing Requirements was introduced
*  Deployed JUnit tests for all DAO methods
*  Postman test for each endpoint was used. 
*  Mocking or stub implementations were used to test services

## Deployment Requirements
---
- The app must be containerized and on dockerhub
* The app must be deployed on an EC2 for your presentation
* The database should be a PostgreSQL on an AWS RDS
