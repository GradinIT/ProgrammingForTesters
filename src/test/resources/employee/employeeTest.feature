Feature: Test employee API
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: the client get employee by id
    When the client request employee id 1
    Then the client get employee 1
  Scenario: client updates name for employee 1
    When the client updates name for employee 1 to Alexander
    Then the name of employee is updated to Alexander
  Scenario: client create employees
    Given the employees
    | 100 | Alexander | Frodi | true | 55000 | 1 |
    | 101 | Joakim | Gradin | true | 65000 | 2 |
    | 100 | Linus | Hellberg | true | 55000 | 3 |
