Feature: test employee api
  Scenario: client gets all employees
    When client calls all employees
    Then client receives 3 employees
  Scenario: client updates name for employee 1
    When client updates name for employee to Gunnar
    Then the name of employee is updated to Gunnar
  Scenario: client gets employee 1
    When client gets employee 1
    Then employee first name is
  Scenario: client updates name for employee 1
    When client updates name for employee to firstName1
    Then the name of employee is updated to firstName1
  Scenario: client deletes employee
    Given all employees
      | 20 | Erik | Svensson | 0 | false | 2 |
    When client deletes employee 20
    Then employee 20 is deleted
    And error message is 404 : [Entity with id 20 not found]
