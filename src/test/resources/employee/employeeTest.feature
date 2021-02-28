Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates first name for employee 1
    When the client updates first name of employee 1 to Jonas
    Then the first name is updated to Jonas
    And the total number of employees is unchanged