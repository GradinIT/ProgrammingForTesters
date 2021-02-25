Feature: test employee api
    Scenario: client gets all employees
        When the client calls /employees
        Then the client receives 3 employees
    Scenario: client updates name for employee 1
        When now the client updates the last name <Carlsson>
        Then now the name is updated to <Carlsson>
    Scenario: client gets Employee 1
        When now the client gets employees 1
        Then now the name of employee is
    Scenario: client deletes employee
        Given the employees
          | 1 | fun
        When the client deletes employee 1
        Then the employee 1 is deleted
