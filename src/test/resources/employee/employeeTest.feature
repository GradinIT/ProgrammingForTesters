Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employees
    Then the client receives 3 employees
  Scenario: client updates name for employee 1
    When the client updates name for employee to <string>
    Then the name is updated to <string>
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the name is
  Scenario: client updates name for employee 1
    When the client updates name for employee to <string>
    Then the name is updated to <string>
  Scenario: client deletes employee
    Given the employees
      | 55 | Anna | Larsson  | | 50000 | | true  | | 1 |
      | 56 | Lena | Qvist    | | 40000 | | true  | | 2 |
      | 57 | Emma | Ivarsson | | 30000 | | false | | 3 |
    When the client deletes employee 55
    When the client deletes employee 56
    When the client deletes employee 57
    Then the department 55 is deleted
    And  the error message is 404 : [Entity with id 55 not found]
    Then the department 56 is deleted
    And  the error message is 404 : [Entity with id 56 not found]
    Then the department 57 is deleted
    And  the error message is 404 : [Entity with id 57 not found]
