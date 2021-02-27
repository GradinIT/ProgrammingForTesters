Feature: test employee api
  Scenario: client gets all employee
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates first name for employee 1
    When the client updates first name for employee to Johanna
    Then the first name is updated to Johanna
  Scenario: client updates last name for employee 1
    When the client updates last name for employee to Employee
    Then the last name is updated to Employee
  Scenario: client updates salary for employee 1
    When the client updates salary for employee to 30000
    Then the salary is updated to 30000
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the first name is

