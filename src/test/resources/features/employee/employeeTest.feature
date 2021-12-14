Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates firstname for employee 1
  #cucumber expression: string in quotes "Runar" gives param {string}
    When the client updates firstname to "Runar" for employee 1
    Then the firstname is updated to "Runar"

  Scenario: Employee firstname is Runar
    When the client gets employee 1
    Then firstname is "Runar"

  Scenario: client updates firstname for employee 1
    When the client updates firstname to "firstName1" for employee 1
    Then the firstname is updated to "firstName1"

  Scenario: Delete Employee
   Given the employees
    | 88 | Socker-Conny | Slagträ | 12500.50 | false | 1 |
    | 89 | Socker-Ronny | Känga | 12500.50 | true | 2 |
     When the client deletes employee 88
     Then the employee 88 is deleted
     And the error message is 404 : ["Entity with id 88 not found"]

    When the client deletes employee 89
    Then the employee 89 is deleted
    And the error message is 404 : ["Entity with id 89 not found"]

   Scenario: Create Employee
     Given new employee
      | 100 | Anna | Andersson |13000 | false | 1 |
      Then the employee 100 exists