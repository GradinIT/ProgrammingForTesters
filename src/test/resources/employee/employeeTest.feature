# new feature
# Tags: optional

Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employees
    Then  the client receives 3 employees
    Scenario: client updates name of employee 1
      When the client updated name for employee to <string>
      Then the name updated to <string>
      Scenario: client gets employee 1
        When the client gets employee 1
        Then the name is
        Scenario: client updates name of employee 1
          When the client updated name for employee to <string>
          Then the name updated to <string>
          Scenario: client deletes employee
            Given the Employees
            | 56 | Slacker |
            | 57 | RageQuiter |
            | 58 | Noob |
            When the client deletes employee 56
            When the client deletes employee 57
            When the client deletes employee 58
            Then the employee 56 was deleted
            And the error message is 404 : [Entity with id 56 not found!]
            Then the employee 57 was deleted
            And the error message is 404 : [Entity with id 57 not found!]
            When the employee 58 was deleted
            And the error message is 404 : [Entity with id 58 not found!]


  Scenario: A scenario
    Given something..