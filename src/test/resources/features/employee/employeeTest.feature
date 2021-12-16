Feature: test employee api

  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates firstname for employee 1
    When the client updates firstname for employee to Runar
    Then the firstname is updated to Runar


  Scenario: client updates lastname for employee 1
    When the client updates lastname for employee to Carola
    Then the lastname is updated to Carola
    
  Scenario: Employee firstname is Runar
    When the client gets employee 1
    Then firstname is Runar

  Scenario: Delete Employee
    Given the employees
      | 88 | Socker-Conny | Lastname | 12500.50 | false | 1 |
      | 89 | Socker-Ronny | Jarnror | 12500.50 | true | 2 |
      | 90 | Socker-Bonny | Lastname | 12500.50 | false | 3 |
    When the client deletes employee 88
    Then the employee 88 is deleted
    And the error message is 404 : ["employee with ID 88 is not found"]




  Scenario: Create employee
      Given new employee
        | 91 | Klas | Kodning | 12500.50 | true | 1 |
      Then the employee 91 exists
      And the client gets message "employee 91 is created"


