Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates first name for employee
    When the client updates firstName for employee to Coder
    Then the firstName is updated to Coder
  Scenario: client updates last name for employee
    When the client updates lastName for employee to Codersson
    Then the lastName is updated to Codersson
  Scenario: client requests name of employee
    When the client requests employee id 1
    Then the client get employee 1 and the first name is Coder
    And the client get employee 1 and the last name is Codersson
  Scenario: client creates employee
    Given the employees
      | 14 | Simon | Anveden | true | 85000 | 1 |
  Scenario: client delete employee
    When the client deletes employee 1
    Then the employee 1 is deleted
