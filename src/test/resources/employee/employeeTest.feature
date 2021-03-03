Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employee


  Scenario: client updates firstname for employee 1
    When the client updates firstname for employee to John
    Then the firstname is updated to John



  Scenario: client updates firstname for employee 1
    When the client updates firstname for employee to firstName1
    Then the firstname is updated to firstName1

  Scenario: client deletes employee
    Given the employees
      | 32 | Pippi| Lol | True | 56000.00  | 1  |
    When the client deletes employee 32
    Then the employee 32 is deleted
    And  the error message is 404 : [Entity with id 32 not found]