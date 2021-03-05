Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates first Name for employee 1
    When the client updates first Name for  to Pelle
    Then the name is updated to pelle
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the first name is
  Scenario: client updates first name for employee 1
    When the client updates first name for employee to firstName1
    Then the name is updated to firstName1
  Scenario: client deletes employee
    Given the employees
      | 21 | Gunnar | Eriksson | true | 10000 | 31 |
      | 22 | Stefan | Petersson | true | 13000 | 32 |
      | 23 | Johan | Andersson | true | 13000 | 33 |

    When the client deletes employee 21
    When the client deletes employee 22
    When the client deletes employee 23
    Then the employee 21 is deleted
    And  the error message is 404 : [Entity with id 21 not found]
    Then the employee 22 is deleted
    And  the error message is 404 : [Entity with id 22 not found]
    Then the employee 23 is deleted
    And  the error message is 404 : [Entity with id 23 not found]
