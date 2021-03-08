Feature: test employee api
  Scenario: --- client gets all employees ---
    When The client asks for all employees
    Then The client receives 4 employees
  Scenario: --- client updates firstname for employee 1 ---  
    When The client updates attributes for employee 1 to robin, hood, 34000, true, 44
    Then The attributes for employeeId 1 are updated to robin, hood, 34000, true, 44
  Scenario: --- client gets Employee 1 ---
    When The client gets employee 1
    Then The attributes are robin, hood, 34000, true, 44
  Scenario: --- client updates firstname for employee 1 ---
    When The client updates attributes for employee 1 to firstName1, lastName1, 25000.00, true, 1
    Then The attributes for employeeId 1 are updated to firstName1, lastName1, 25000.00, true, 1
  Scenario: --- client gets Employee 1 ---
    When The client gets employee 1
    Then The attributes are firstName1, lastName1, 25000.00, true, 1
  Scenario: --- client gets Employee 2 ---
    When The client gets employee 2
    Then The attributes are firstName2, lastName2, 25000.00, true, 1
  Scenario: --- client updates name for employee 3
    When The client updates attributes for employee 3 to Alex3, Ek3, 34321.50, true, 303034
    Then The attributes for employeeId 3 are updated to Alex3, Ek3, 34321.50, true, 303034
  Scenario: --- client gets Employee 3 ---
    When The client gets employee 3
    Then The attributes are Alex3, Ek3, 34321.50, true, 303034
  Scenario: --- client updates name for employee 3 ---
    When The client updates attributes for employee 3 to firstName3, lastName3, 25000.00, true, 1
    Then The attributes for employeeId 3 are updated to firstName3, lastName3, 25000.00, true, 1
  Scenario: --- client gets Employee 3 ---
    When The client gets employee 3
    Then The attributes are firstName3, lastName3, 25000.00, true, 1
  Scenario: --- client creates and deletes employees ---
    Given The employees
      | 33 | firstFirstName | firstLastName | 33000 | true | 4 |
      | 34 | secondLastName | secondLastName | 55000 | false | 4 |
      | 35 | thirdFirstname | thirdLastName | 77000 | true | 3 |
    When The client deletes employee 33
    When The client deletes employee 34
    When The client deletes employee 35
    Then The employee 33 is deleted, checked by failed 'get employee'
    And  The error message is 404 : [Entity with id 33 not found]
    Then The employee 34 is deleted, checked by failed 'get employee'
    And  The error message is 404 : [Entity with id 34 not found]
    Then The employee 35 is deleted, checked by failed 'get employee'
    And  The error message is 404 : [Entity with id 35 not found]
  Scenario: --- client tries to delete non-existent employee ---
    When The client tries to delete non-existent employee with id 200
    Then The delete is not OK with thrown exception
    And  The delete error message is 404 : [Entity with id 200 not found]
