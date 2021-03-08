Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates name for employee 1
    When the client updates name for employee to Robert
    Then this name is updated to Robert
    When the client updates name for employee to firstName1
    Then this name is updated to firstName1
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then this name is
  Scenario: client deletes employee
    Given the employees
   #  |empId|fName   |lName   |salary|fTime|depId|
      |4    |Robert  |Tenglund|50000 |True |1    |
      |13    |Blurb   |Glurb   |40000 |True |1    |
      |14    |Kalle   |Anka    |30000 |false|1    |
    When the client deletes employee 4
    Then the employee 4 is deleted
    And Error 404, the employee 4 not found

    When the client deletes employee 13
    Then the employee 13 is deleted
    And Error 404, the employee 13 not found

    When the client deletes employee 14
    Then the employee 14 is deleted
    And Error 404, the employee 14 not found


