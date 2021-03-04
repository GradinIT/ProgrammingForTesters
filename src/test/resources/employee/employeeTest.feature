Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates first name for employee 1
    When the client updates name for employee 1 to "Sven" "Svensson"
    Then the employee name is updated to "Sven" "Svensson"
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the name is Sven Svensson
  Scenario: client updates first name for employee 1
    """ Reusing the Scenario and @When/@Then on line 5-7 to return the values to starting values """
    When the client updates name for employee 1 to "firstName1" "lastName1"
    Then the employee name is updated to "firstName1" "lastName1"
  Scenario: client creates and deletes employees 501 to 503
    Given the employees
      | 501 | Anders | Andersson | true | 31000 | 1 |
      | 502 | Stina | Stenkvist | true | 35000 | 2 |
      | 503 | Karl | Karlenius | true | 27000 | 3 |
    When the client deletes employee 501
    When the client deletes employee 502
    When the client deletes employee 503
    Then the employee 501 is deleted
    And  the employee error message is 404 : [Entity with id 501 not found]
    Then the employee 502 is deleted
    And  the employee error message is 404 : [Entity with id 502 not found]
    Then the employee 503 is deleted
    And  the employee error message is 404 : [Entity with id 503 not found]
