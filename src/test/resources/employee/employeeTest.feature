Feature: test employee api
  Scenario: client gets all employees
    When The client calls /employee
    Then The client receives 4 employees
  Scenario: client updates firstname for employee 1
    When The client updates attributes for employee 1 to robin, hood, 34000, true, 44
    Then The attributes for employeeId 1 are updated to robin, hood, 34000, true, 44
  Scenario: client gets Employee 1
    When The client gets employee 1
    Then The name is robin hood
  Scenario: client updates firstname for employee 1
    When The client updates attributes for employee 1 to firstName1, lastName1, 25000.00, true, 1
    Then The attributes for employeeId 1 are updated to firstName1, lastName1, 25000.00, true, 1
  Scenario: client gets Employee 1
    When The client gets employee 1
    Then The name is firstName1 lastName1
  Scenario: client gets Employee 2
    When The client gets employee 2
    Then The name is firstName2 lastName2
  Scenario: client updates name for employee 3
    When The client updates attributes for employee 3 to Alex3, Ek3, 34321.50, true, 303034
    Then The attributes for employeeId 3 are updated to Alex3, Ek3, 34321.50, true, 303034
  Scenario: client gets Employee 3
    When The client gets employee 3
    Then The name is Alex3 Ek3
  Scenario: client updates name for employee 3
    When The client updates attributes for employee 3 to firstName3, lastName3, 25000.00, true, 1
    Then The attributes for employeeId 3 are updated to firstName3, lastName3, 25000.00, true, 1
  Scenario: client gets Employee 3
    When The client gets employee 3
    Then The name is firstName3 lastName3
  Scenario: client deletes employee
    Given The employees
      | 33 | firstFirstname | firstlastname | 33000 | true | 4 |
      | 34 | secondlastname | secanlastname | 55000 | false | 4 |
      | 35 | thirdFirstname | thirddddlastname | 77000 | true | 3 |
    When The client deletes employee 33
    When The client deletes employee 34
    When The client deletes employee 35
    Then The employee 33 is deleted, checked by failed getId
    And  The error message is 404 : [Entity with id 33 not found]
    Then The employee 34 is deleted, checked by failed getId
    And  The error message is 404 : [Entity with id 34 not found]
    Then The employee 35 is deleted, checked by failed getId
    And  The error message is 404 : [Entity with id 35 not found]
  Scenario: client tries to delete non-existant employee
    When The client tries to delete non-existant employee with id 100
    Then The delete should be OK although non-existant object, that is idempotent delete

