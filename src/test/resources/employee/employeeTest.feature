Feature: test employee api
Scenario: client gets all employees
When the client calls /employee
Then the client receives 3 employees
Scenario:  client gets employee by id
  When the client calls employee 1
  Then the client receives employeeDetails
Scenario:  client deletes employee
  Given the employees
  | 1 |
  When the client deletes employee 1
  Then the client 1 is deleted
  And error message is 404 : [Entity with id 1 not found]
  Scenario: client updates employee by id
    When  the client updates employee 2 new firstname Darth and lastname Vader
    Then the employee 2 is updated and new firstname is Darth and lastname Vader
