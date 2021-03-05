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
    Given the employees are

      | 1 | Nico  | Palucchi | 30000 | True | 1
      | 2 | Astrid| Sinabian | 35000 | True | 1
      | 3 | Eddie | Diaz     | 40000 | True | 1

    When the client deletes this employee 1
    Then the employee 1 is now deleted


    When the client deletes department 1

    When the client deletes department 2
    When the client deletes department 3
    Then the department 1 is deleted
    And  error message is 404 : [Entity with id 1 not found]
    Then the department 2 is deleted
    And  error message is 404 : [Entity with id 2 not found]
    Then the department 3 is deleted
    And  error message is 404 : [Entity with id 3 not found]
