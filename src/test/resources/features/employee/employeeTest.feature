Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

    Scenario: client creates employee
      When client calls /createemployee