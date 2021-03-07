Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates name for employee 1
    When the client updates firstName for employeeId 1 to Agda
    Then the firstName is updated to Agda

  Scenario: client updates name for employee 1
      When the client updates firstName for employeeId 1 to firstName1
      Then the firstName is updated to firstName1

  Scenario: client gets EmployeeId 1
    When the client gets employeeId 1
    Then the firstName is "firstName1"

  Scenario: client updates salary for employee 1
    When the client updates salary for employeeId 1 to 30000
    Then the salary is updated to 30000

  Scenario: client updates salary for employee 1
     When the client updates salary for employeeId 1 to 25000
     Then the salary is updated to 25000

  Scenario: client deletes employees
    Given the employee
    | 34 | Mathilda | Fritzell | 100000 | true | 1 |
    | 24 | Hej   | Hå | 20000  |false | 2 |
    When the client deletes employee 34
    Then the employee 34 is deleted
    And the employee error message is 404 : [Entity with id 34 not found]
    When the client deletes employee 24
    Then the employee 24 is deleted
    And the employee error message is 404 : [Entity with id 24 not found]

  Scenario: client creates a new employee
   When the client creates an employee with id 29
   Then the employee with id 29 exists

   Scenario: client creates the same employee
      When the client creates a duplicate employee with id 29
      Then the client receives an error

   Scenario: client deletes employee
     When the client deletes employee 29
     Then the employee 29 is deleted
     And the employee error message is 404 : [Entity with id 29 not found]