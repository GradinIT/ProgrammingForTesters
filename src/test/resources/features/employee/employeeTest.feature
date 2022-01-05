Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates name for employee 1
    When the client updates name for employee to firstName1 and lastName1
    Then the name is updated to firstName1 and lastName1
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the name is
  Scenario: client deletes employee
    Given the employees
      | 4 | firstName4 | lastName4 |
      | 5 | firstName5 | lastName5 |
      | 6 | firstName6 | lastName6 |
    When the client deletes employee 4
    When the client deletes employee 5
    When the client deletes employee 6
    Then the employee 4 is deleted
    And  the error message is 404 : ["Employee with id 4 not found"]
    Then the employee 5 is deleted
    And  the error message is 404 : ["Employee with id 5 not found"]
    Then the employee 6 is deleted
    And  the error message is 404 : ["Employee with id 6 not found"]