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
  Scenario: client updated firstname for employee 1
    When the client updates name for employee to Kajsa
    Then the employees name is updated to Kajsa
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