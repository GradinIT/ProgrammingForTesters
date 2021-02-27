Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employee
  Scenario: client updates firstname for employee 1
    When the client updates firstName for employee to Stina
    Then the firstName is updated to Stina
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the firstName is
  Scenario: client deletes employee
    Given the employees
      | 10 | Eva | Ek | TRUE | 100000.00 | 1 |
    When the client deletes employee 10
    Then the employee 10 is deleted

