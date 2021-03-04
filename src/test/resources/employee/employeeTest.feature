Feature: test employee api
  Scenario: client gets all employees
    When calls /employee
    Then receives 3 employees
  Scenario: client updates lastName for employee 1
    When updates lastName for employee to Everest
    Then the lastName is updated to Everest
  Scenario: client gets Employee 1
    When gets Employee 1
    Then the lastName of employee is
  Scenario: client updates lastName for employee 1
    When updates lastName for employee to lastName1
    Then the name is updated to lastName1
  Scenario: client deletes employee
    Given the employees
      | 23 | Kebne  | Kajse   | 45000 | true  | 1 |
      | 24 | Vattna | Yokull  | 35000 | false | 2 |
      | 25 | Mount  | Everest | 40000 | true  | 1 |
    When deletes employee 23
    When deletes employee 24
    When deletes employee 25
    Then the employee 23 is deleted
    And  the error message is 404 : [Entity with id 23 not found]
    Then the employee 24 is deleted
    And  the error message is 404 : [Entity with id 24 not found]
    Then the employee 25 is deleted
    And  the error message is 404 : [Entity with id 25 not found]
