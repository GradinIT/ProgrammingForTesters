Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: update employee lastname for employee 1
    When updates lastname to Pellesson
    Then the lastname is updated to pellesson
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the lastname is
  Scenario: update employee lastname for employee 1
    When updates lastname for employee to lastName1
    Then the name is updated to lastName1
  Scenario: client deletes employee
    Given the employees
      | 21 | Gunnar | Eriksson | true | 10000 | 31 |

    When the client deletes employee 21
    Then the employee 21 is deleted
    And  the error message is 404 : [Entity with id 21 not found]

