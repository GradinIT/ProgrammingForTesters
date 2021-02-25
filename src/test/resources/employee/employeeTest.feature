Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 7 employees
  Scenario: client updates name for employee 1
    When the client updates name for employee to Hello
    Then the name is updated to Hello
