Feature: test employee api
  Scenario client gets all employees
    When the client calls /employees
    Then the client receives 3 employees