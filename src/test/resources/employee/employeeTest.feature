Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 7 employees
  Scenario: client updates information for employee 2
    When the client updates information for employee to Hello, Gea, 30000.00, true and 10
    Then the employee information is updated to Hello, Gea, 30000.00, true and 10
  Scenario: client gets Employee 2
    When the client gets employees 2
    Then the information on the employee is
  Scenario: client deletes employee
    Given the employees
      | 101 | Test    | Testarsson | true | 100 | 78000 |
      | 102 | NoExist | No         | true | 200 | 58000 |
      | 103 | Ghost   | Ghostsson  | true | 300 | 98000 |
    When the client deletes employee 101
    When the client deletes employee 102
    When the client deletes employee 103
    Then the employee 101 is deleted
    And  the error message is 404 : [Entity with id 101 was not found]
    Then the employee 102 is deleted
    And  the error message is 404 : [Entity with id 102 was not found]
    Then the employee 103 is deleted
    And  the error message is 404 : [Entity with id 103 was not found]

