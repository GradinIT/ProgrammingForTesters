Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates name for employee 1
    When updates firstname for employee to firstName1
    Then the firstname is updated to firstName1
  Scenario: client gets employee 1
    When the client gets employee 1
    Then the firstname is
  Scenario: client updates firstname for employee 1
    When updates firstname for employee to firstName1
    Then the firstname is updated to firstName1
  Scenario: client deletes employee
    Given the employees
      | 55 | Max | Martin  | 25000 | true | 1 |
    When the client deletes employee 55
    Then the employee 55 is deleted
    And  the employee error message is 404 : [Entity with id 55 not found]