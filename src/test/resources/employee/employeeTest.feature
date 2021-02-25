Feature: Test employee API
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
