Feature: test employee api
  Scenario: client gets all employees
    When The client calls /employee
    Then The client receives 3 employees

  Scenario: client updates name for employee 1
    When The client updates attributes for employee to geran,goransson,32232,true,45
    Then The attribute is updated to geran,goransson,32232,true,45

  Scenario: client updates name for employee 3
    When The client updates attributes for employee to Alex3,Ek3,34321,true,303034
    Then The attribute is updated to Alex3,Ek3,34321,true,303034



  Scenario: client gets Employee 1
    When The client gets employee 1
    Then The name is

    #Scenario: client gets Employee 3
     # When The client gets employee 3
      #Then The name is

  Scenario: client updates name for employee 1
    When The client updates attributes for employee to firstName1,lastName1,25000,true,1
    Then The attribute is updated to firstName1,lastName1,25000,true,1

  Scenario: client deletes employee
    Given The employees
      | 32 | firstFirstName | firstLastName | 42422 | true | 23|
      | 33 | secondSecondName | secondLastName | 42234 | false | 44 |
      | 34 | thirdThirdName | thirdLastName    | 4242412 | true | 55 |
  When The client deletes employee 32
  When The client deletes employee 33
  When The client deletes employee 34
  Then The employee 32 is deleted, checked by failed getId
  And  The error message is 404 : [Entity with id 32 not found]
  Then The employee 33 is deleted, checked by failed getId
  Then The employee 34 is deleted, checked by failed getId
  Scenario: client tries to delete non-existant employee
    When The client tries to delete non-existant employee with id 33
    Then The delete should be OK although non-existant object, that is idempotent delete
