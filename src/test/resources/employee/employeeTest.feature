Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employees
    Then the client receives 3 employees
  Scenario: client updates first name for employee 1
    When the client updates name for employee to Kalle
    Then the first name is updated to Kalle
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the first name is
  Scenario: client updates name for employee 1
    When the client updates name for employee to Anna
    Then the first name is updated to Anna
  Scenario: client deletes employee
    Given the employees
      | 55 | Anna | Larsson  | 50000 | true  | 1 |
      | 56 | Lena | Qvist    | 40000 | true  | 2 |
      | 57 | Emma | Ivarsson | 30000 | false | 3 |
    When the client deletes employee 55
    When the client deletes employee 56
    When the client deletes employee 57
    Then the employee 55 is deleted
    And  the error message for employee is 404 : [Entity with id 55 not found]
    Then the employee 56 is deleted
    And  the error message for employee is 404 : [Entity with id 56 not found]
    Then the employee 57 is deleted
    And  the error message for employee is 404 : [Entity with id 57 not found]
