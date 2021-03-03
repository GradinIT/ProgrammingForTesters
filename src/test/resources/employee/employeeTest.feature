Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: update employee lastname for employee 1
    When updates lastname for employee to Coding
    Then the lastname is updated to Coding
  Scenario: client gets employee 1
    When the client gets employee 1
    Then the lastname is
  Scenario: update employee lastname for employee 1
    When updates lastname for employee to lastName1
    Then the lastname is updated to lastName1
  Scenario: client deletes employee
    Given the employees
      | 55 | Linda | Andersson | 25000 | true | 1 |
    When the client deletes employee 55
    Then the employee 55 is deleted