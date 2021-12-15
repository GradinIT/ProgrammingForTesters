Feature: test employee api
  Scenario: client gets all employees
    When the client calls employee
    Then the client receives 3 employees
  Scenario: client updates firstname for employee 1
    When the client updates firstname for employee 1 to Runar
    Then the firstname is updated to Runar
  Scenario: Employee firstname is Runar
    When the client gets employee 1
    Then firstname is Runar
    And the client updates firstname for employee 1 to Fagel
  Scenario: Delete Employee
    Given the employees
      | 88 | Socker-Conny | Slagträ | 12500.50 | false | 1 |
      | 89 | Socker-Ronny | Känga | 12500.50 | true | 2 |
      | 90 | Socker-Bonny | Knogjärn | 12500.50 | false | 3 |
    When the client deletes employee 88
    When the client deletes employee 89
    When the client deletes employee 90
    Then the employee 88 is deleted
    And  the error message is 404 : ["Entity with id 88 not found"]
    Then the employee 89 is deleted
    And  the error message is 404 : ["Entity with id 89 not found"]
    Then the employee 90 is deleted
    And  the error message is 404 : ["Entity with id 90 not found"]