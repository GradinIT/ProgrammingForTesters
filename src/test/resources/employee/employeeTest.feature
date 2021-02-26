Feature: test employee api

  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates first name
    When the client updates first name of employee 1 to Jonas
    Then the name of employee 1 is updated to Jonas
    And number of employees is checked

  Scenario:
