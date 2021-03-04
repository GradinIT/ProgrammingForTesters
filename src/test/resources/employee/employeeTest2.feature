Feature: test employee api

  Scenario: client gets all employees
    When the client calls /employees
    Then the client receives all 3 employees

  Scenario: client updates first name of employee to
    When the client updates first name of employee to Nico
    Then the first name is updated to Nico

  Scenario: client updates last name of employee
    When the client updates last name of employee to Palucchi
    Then the last name is updated to Palucchi
    
  Scenario: client updates salary of employee to
    When the client updates salary of employee to 31000
    Then the salary is updated to 31000

  Scenario: client updates contract
    When the client updates contract of employee to full time true
    Then the contract is updated to true

  Scenario: client gets employee by id
    When the client gets employee by id 1
    Then the name of employee is

  Scenario: client deletes employee
    Given the employees

      | 1 | Nico  | Palucchi | 30000 |
      | 2 | Astrid| Sinabian | 35000 |
      | 3 | Eddie | Diaz     | 40000 |
    When the client deletes employee 1
    Then the employee 1 is deleted
    When the client deletes department 55
    When the client deletes department 56
    When the client deletes department 57
    Then the department 55 is deleted
    And  the error message is 404 : [Entity with id 55 not found]
    Then the department 56 is deleted
    And  the error message is 404 : [Entity with id 56 not found]
    Then the department 57 is deleted
    And  the error message is 404 : [Entity with id 57 not found]
