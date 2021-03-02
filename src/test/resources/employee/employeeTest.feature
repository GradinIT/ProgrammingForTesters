Feature: test employee api
  Scenario: client gets all employees
    When client calls all employees
    Then client receives 3 employees
  Scenario: client updates name for employee 1
    When client updates name for employee to Gunnar lastname Gunnarsson
    Then the name of employee is updated to Gunnar lastname Gunnarsson
  Scenario: client gets employee 1
    When client gets employee 1
    Then employee first name is Gunnar
  Scenario: client updates name for employee 1
    When client updates name for employee to firstName1 lastname lastName1
    Then the name of employee is updated to firstName1 lastname lastName1
  Scenario: client deletes employee
    Given all employees
      | 20 | Erik | Svensson | 0 | false | 2 |
      | 21 | Stefan | Svensson | 0 | false | 2 |
    When client deletes employee 20
    Then employee 20 is deleted
    And error message is 404 : [Entity with id 20 not found]
    When client deletes employee 21
    Then employee 21 is deleted
    And error message is 404 : [Entity with id 21 not found]