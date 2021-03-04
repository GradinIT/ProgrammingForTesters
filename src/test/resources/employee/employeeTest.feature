Feature: test employee api

  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates name for employee 1
    When the client updates employee to Nico
    Then the employee is updated to Nico
  Scenario: client gets employee by id 1
    When the client gets employee 1
    Then the name is

  Scenario: client deletes employee
    Given the employees

      | 1 | Nico  | Palucchi | 30000 |
      | 2 | Astrid| Sinabian | 35000 |
      | 3 | Eddie | Diaz     | 40000 |
    When the client deletes employee 1
    Then the employee 1 is deleted
    When the client deletes department 55
    When the client deletes department 56
    When the client deletes department 57
    Then the department 55 is deleted
    And  the error message is 404 : [Entity with id 55 not found]
    Then the department 56 is deleted
    And  the error message is 404 : [Entity with id 56 not found]
    Then the department 57 is deleted
    And  the error message is 404 : [Entity with id 57 not found]
