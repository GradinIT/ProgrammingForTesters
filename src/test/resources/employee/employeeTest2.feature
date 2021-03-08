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

      | 14 | Nico  | Palucchi | 31000 | True | 1 |

    When the client deletes this employee 14
    Then the employee 14 is now deleted
