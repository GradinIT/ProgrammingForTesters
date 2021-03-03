Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employees
    Then the client receives 3 employees
  Scenario: client updates firstname for employee 1
    When the client updates name for employee to Egon
    Then the name updated to Egon
  Scenario: client gets employee 1
    When the client gets employee 1
    Then the name is