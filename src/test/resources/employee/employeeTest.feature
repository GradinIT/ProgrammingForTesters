Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 4 employees
  Scenario: client updates name for employee 1
    When the client updates first name for employee to firstName
    Then the employee first name is updated to firstName
  Scenario: client gets employee 1
    When the client gets employee 1
    Then the name of employee is
  Scenario: client deletes employee
    Given the employees
      | 55 | Fun |
    When the client deletes employee 55
    Then the employee 55 is deleted