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
      | 77 | hej |
      | 78 | hello |
      | 79 | hi |
    When the client deletes employee 77
    When the client deletes employee 78
    When the client deletes employee 79
    Then the employee 77 is deleted
    And  the error is 404 : [Entity with id 77 not found]
    Then the employee 78 is deleted
    And  the error is 404 : [Entity with id 78 not found]
    Then the employee 79 is deleted
    And  the error is 404 : [Entity with id 79 not found]
