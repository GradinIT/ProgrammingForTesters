Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates name for employee 1
    When the client updates name for employee to Robert
    Then this name is updated to Robert
  Scenario: client gets Employee 1
    When the client gets employee 1
    Then this name is
  Scenario: client deletes employee
    Given the employees
   #  |empId|fName   |lName   |salary|fTime|depId|
      |12    |Robert  |Tenglund|50000 |True |1    |
      |13    |Blurb   |Glurb   |40000 |True |1    |
      |14    |Kalle   |Anka    |30000 |false|1    |
    When the client deletes employee 12
    Then the employee 12 is deleted
    And Error 404, the employee 12 not found