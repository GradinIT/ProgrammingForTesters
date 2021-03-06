Feature: test employee api

  #HappyFlows
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates first name
    When the client calls employee 1
    Then employee 1 is found
    When the client updates first name of employee 1 to Jonas
    Then the name of employee 1 is updated to Jonas
    When the client updates first name of employee 1 to firstName1
    Then the name of employee 1 is updated to firstName1

  Scenario: the client deletes employee
    Given the employee
      | 10 | Nalle | Puh | 27000 | 1 | 2 |
    And the total number of employees is 4
    When the client deletes employee 10
    Then employee 10 is no longer found in search
    And the total number of employees is 3

  #Felflöden
  Scenario: searching for non-existent employee
    When the client tries to get employee 42
    Then employee 42 not found exception is thrown
