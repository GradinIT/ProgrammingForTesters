Feature: test employee api

  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates name for employee 1
    When the client updates first name of employee to Nico
    Then the first name is updated to Nico

  Scenario: client updates last name for employee 1
    When the client updates last name of employee to Palucchi
    Then the last name is updated to Palucchi

  Scenario: client updates salary for employee 1
    When the client updates salary of employee to 32500
    Then the salary is updated to 32500

  Scenario: client updates contract for employee 1
    When the client updates contract of employee to full time true
    Then the contract is updated to true

  Scenario: client gets Department 1
    When the client gets department 1
    Then the name is

  Scenario: client updates name for department 1
    When the client updates name for department to Development
    Then the name is updated to Development

  Scenario: client deletes department
    Given the departments
      | 55 | Fun  |
      | 56 | HR   |
      | 57 | Fika |
    When the client deletes department 55
    When the client deletes department 56
    When the client deletes department 57
    Then the department 55 is deleted
    And  the error message is 404 : [Entity with id 55 not found]
    Then the department 56 is deleted
    And  the error message is 404 : [Entity with id 56 not found]
    Then the department 57 is deleted
    And  the error message is 404 : [Entity with id 57 not found]
