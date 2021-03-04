Feature: test employee api

  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates first name
    When the client updates first name of employee 1 to Jonas
    Then the name of employee 1 is updated to Jonas
    And the total number of employees is still 3

  Scenario: the client deletes employee
    Given the employee
    | 10 | Jonas | Svensson | 27000 | 1 | 2 |
    When the client deletes employee 10
    Then employee 10 is deleted

  Scenario: searching for non-existent employee
    When the client tries to get employee 42
    Then employee 42 not found exception is thrown

  #Scenario: adding a new employee
  #  When the client adds an employee with id 42
  #  Then employee with id 42 is added
  #  And the number of employees is 4

  #Scenario: deleting number 42
  #  When the client deletes employee 42
  #  Then employee 42 is deleted
  #  And the number of employees is back to 3

  #Scenario: adding another employee 1
  #  When the client adds an employee with id 1
  #  Then the entity is already in storage
  #  And an exception is thrown