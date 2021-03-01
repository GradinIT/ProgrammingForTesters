Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates firstName for employee 1
    When the client updates firstName for employee to Coder
    Then the firstName is updated to Coder
  Scenario: client updates lastName for employee 1
    When the client updates lastName for employee to Codersson
    Then the lastName is updated to Codersson
