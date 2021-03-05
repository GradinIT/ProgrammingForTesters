Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates name for employee 1
    When the client updates name for employee to Test
    Then the employee name is updated to Test
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the employee name is
  Scenario: client updates name for employee 1
    When the client updates name for employee to firstName1
    Then the employee name is updated to firstName1
  Scenario: the client deletes employee
    Given the employees
      | 12 | Kalle | Kallesson | 4000 | TRUE | 1 |
      | 13 | Adam | Adamsson  | 3000 | TRUE | 2 |
      | 14 | Erik | Eriksson | 2000 | FALSE | 3 |
    When the client deletes employee 12
    When the client deletes employee 13
    When the client deletes employee 14
    Then the employee 12 is deleted
    And  the error message 404 : [Entity with id 12 not found]
    Then the employee 13 is deleted
    And  the error message 404 : [Entity with id 13 not found]
    Then the employee 14 is deleted
    And  the error message 404 : [Entity with id 14 not found]
