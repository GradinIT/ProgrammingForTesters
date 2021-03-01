Feature: Test employee API
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates name for employee 1
    When the client updates name for employee 1 to Alexander
    Then the name of employee is updated to Alexander
  Scenario: the client get employee by id
    When the client request employee id 1
    Then the client get employee 1 and the name is Alexander
    Scenario: client updates name for employee 1
      When the client updates name for employee 1 to firstName1
      Then the name of employee is updated to firstName1
  Scenario: client create employees
    Given the employees
    | 100 | Alexander | Frodi | true | 55000 | 1 |
    | 101 | Joakim | Gradin | true | 65000 | 2 |
    | 102 | Linus | Hellberg | true | 55000 | 3 |
  Scenario: client delete employee
    When the client delete employee 100
    Then the employee 100 is deleted
    And error code is 404 : [Entity with id 100 not found]
    When the client delete employee 101
    Then the employee 101 is deleted
    And error code is 404 : [Entity with id 101 not found]
    When the client delete employee 102
    Then the employee 102 is deleted
    And error code is 404 : [Entity with id 102 not found]