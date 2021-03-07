Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employees
    Then the client receives 3 employees
  Scenario: client updates firstname for employee 1
    When the client updates name for employee to Egon
    Then the employees name is updated to Egon
  Scenario: client gets employee 1
    When the client gets employee 1
    Then the employees name is
  Scenario: client updates lastname for employee 1
    When the client updates lastname for employee to Egonson
    Then the employee lastname is updated to Egonson
    Scenario: client gets employee 1
      When the client gets employee 1
      Then the employee lastname is
  Scenario: client updates salary for employee 1
    When the client updates salary for employee to 20000
    Then the employee salary is updated to 20000
  Scenario: client updates full time for employee 1
    When the client updates full time for employee to false
    Then the employee full time is updated to false
  Scenario: client updates department for employee 1
    When the client updates department for employee to 2
    Then the employee department is updated to 2

  Scenario: client updated firstname for employee 1
    When the client updates name for employee to EmployeeName
    Then the employees name is updated to EmployeeName
  Scenario: client deletes employee
    Given the employees
      | 10 | oskar | franck | 25000 | true | 1 |
      | 11 | sandra | bjarevall | 25000 | true | 1 |
    When the client deletes employee 10
    When the client deletes employee 11
    Then the employee 10 is deleted
    And the employee error message is 404 : [Entity with id 10 not found]
    Then the employee 11 is deleted
    And the employee error message is 404 : [Entity with id 11 not found]