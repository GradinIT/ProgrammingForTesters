Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates name for employee 1
    When the client updates name for employee to Coding
    Then the name is updated to Coding
  Scenario: client gets employee 1
    When the client gets employee 1
    Then the name is
  Scenario: client updates name for employee 1
    When the client updates name for employee to Development
    Then the name is updated to Development
  Scenario: client deletes employee
    Given the employees
      | 55 | Fun |
    When the client deletes employee 55
    Then the employee 55 is deleted