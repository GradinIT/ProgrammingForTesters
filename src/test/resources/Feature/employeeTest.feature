@Employee
Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employees
    Then  the client receives 3 employees
    Scenario: client updates name of employee 1
      When the client updated name for employee to Tyr
      Then the name updated to Tyr
      Scenario: client gets employee 1
        When the client gets employee 1
        Then the name is
        Scenario: client updates name of employee 1
          When the client updated name for employee to FirstName1
          Then the name updated to FirstName1
          Scenario: client deletes employee
            Given the Employees
            | 56 | Rang | HammerFell | 25000 | false |
            | 57 | Bibidi  | Hopidi | 30000 | false |
            | 58 | Bob | Builder | 27000 | false |
            When the client deletes employee 56
            When the client deletes employee 57
            When the client deletes employee 58
            Then the employee 56 was deleted
            And the error message is 404 : [Entity with id 56 not found!]
            Then the employee 57 was deleted
            And the error message is 404 : [Entity with id 57 not found!]
            When the employee 58 was deleted
            And the error message is 404 : [Entity with id 58 not found!]