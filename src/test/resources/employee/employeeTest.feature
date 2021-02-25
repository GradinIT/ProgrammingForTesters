Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: The client updates firstname for employee 1
    When the client updates firstname for employee to adam
    Then the firstname is updated to adam
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the name is
  Scenario: client updates firstname for employee 1
    When the client updates firstname for employee to serhat
    Then the firstname is updated to serhat
  Scenario: client deletes employee
    Given the employee
      | 20 | Henrik | Lindkvist | False | 30000.00 | 1 |
    When the client deletes employee 20
    Then the department 20 is deleted





