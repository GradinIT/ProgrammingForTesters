Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates everything for employee 1
    When the client updates everything for employee to Coding
    Then the employee is updated to Coding
  Scenario: client gets employee 1
    When the client gets employee 1
    Then employees first name is
  Scenario: client updates name for employee 1
    When the client updates everything for employee to firstName1
    Then the employee is updated to firstName1
  Scenario: client deletes employee
    Given the employees
      | 10 | Anders | Hellman | true | 1000000.00 | 22 |
      | 11 | Mnders | Helman | true | 1000000.00 | 23 |
    When the client deletes employee 10
    Then the employee 10 is deleted
    And  the error is 404 : [Entity with id 10 not found]
    When the client deletes employee 11
    Then the employee 11 is deleted
    And  the error is 404 : [Entity with id 11 not found]