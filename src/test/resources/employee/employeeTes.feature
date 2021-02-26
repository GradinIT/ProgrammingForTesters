Feature: test employee api
  Scenario: client gets all employee
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates first name for employee 1
    When the client updates first name for employee to Coding
    Then the name is updated to Coding
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the name is
  Scenario: client updates name for employee 1
    When the client updates name for employee to Employee
    Then the name is updated to Employee