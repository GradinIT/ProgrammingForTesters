Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates first name for employee
    When the client updates firstName for employee to firstName1
    Then the firstName is updated to firstName1
  Scenario: client updates last name for employee
    When the client updates lastName for employee to lastName1
    Then the lastName is updated to lastName1
  Scenario: client requests name of employee
    When the client requests employee id 1
    Then the client get employee 1 and the first name is firstName1
    And the client get employee 1 and the last name is lastName1
  Scenario: client delete employee
    Given the employees
      | 14 | Simon | Anveden | true | 85000 | 1 |
    When the client deletes employee 14
    Then the employee 14 is deleted
