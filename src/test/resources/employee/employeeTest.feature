Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 4 employees
  Scenario: client updates name for employee 1
    When the client updates name for employee to Robert
    Then the name is updated to Robert
  Scenario: client gets Department 1
    When the client gets department 1
    Then the name is
  Scenario: client updates name for department 1
    When the client updates name for department to Development
    Then the name is updated to Development
  Scenario: client deletes department
    Given the departments
      | 55 | Fun |
    When the client deletes department 55
    Then the department 55 is deleted