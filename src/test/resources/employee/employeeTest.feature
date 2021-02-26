Feature: test employee api

  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates name for employee 1
    When the client updates name for employee to Ludde Ljungman
    Then the employee name is updated to Ludde Ljungman

  Scenario: client gets employee 1
    When the client gets employee 1
    Then the info is

  Scenario: client updates name for employee 1
    When the client updates name for employee to Fabian Ekbäck
    Then the employee name is updated to Fabian Ekbäck

  Scenario: client deletes employee
    Given the employees
      | 4 | Ludde | Ljungman | 260000.0 | TRUE | 1 |
    When the client deletes employee 4
    Then the employee 4 is deleted
    And  the employee error message is 404 : [Entity with id 4 not found]
