Feature: test employee api

  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: The client updates firstname for employee 1
    When the client updates firstname for employee to Adam
    Then the firstname is updated to Adam

  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the firstname is

  Scenario: client updates firstname for employee 1
    When the client updates firstname for employee to Serhat
    Then the firstname is updated to Serhat

  Scenario: client deletes employee
    Given the employees
      | 20 | Henrik | Lindkvist | false | 30000.00 | 1 |
    When the client deletes employee 20
    Then the employee 20 is deleted
    And the error message is 404 : [Employee with id 20 not found]





