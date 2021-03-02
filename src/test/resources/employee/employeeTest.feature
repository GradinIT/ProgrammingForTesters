Feature: test employee api

  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates first name
    When the client updates first name of employee 1 to Jonas
    Then the name of employee 1 is updated to Jonas
    And the total number of employees is unchanged

  Scenario: the client deletes employee
    Given the employee
    | 10 | Jonas | Svensson | 27000 | 1 | 2 |
    When the client deletes employee 10
    Then employee 10 is deleted
