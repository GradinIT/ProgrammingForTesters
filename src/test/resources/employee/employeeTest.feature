Feature: test employee api
  Scenario: client gets all employees
    When calls /employee
    Then receives 4 employees
  Scenario: client updates lastName for employee 1
    When updates lastName for employee to Everest
    Then the lastName is updated to Everest
  Scenario: client gets employee 1
    When the client gets employee 1
    Then the lastName is
#  Scenario: client updates lastName for employee 1
#    When updates lastName for employee to lastName1
#    Then the name is updated to lastName1  # Error due to difference in text, correct line under here
  Scenario: client updates lastName for employee 1
    When updates lastName for employee to lastName1
    Then the lastName is updated to lastName1
  Scenario: the client deletes employee
    Given the employees
      | 55 | Kebne  | Kajse   | 45000 | true  | 1 |
#      | 56 | Vattna | Yokull  | 35000 | false | 2 |   #When building application in Maven this integration test seems to be run before the TestEmployeeSuit.
#      | 57 | Mount  | Everest | 40000 | true  | 1 |   # These two lines makes the TestSuit think that there are two more employees in the database then expected. If these employees are activated, they must be deleted within the same scenario to not spoil the test suit.
    When deletes employee 55
#    When deletes employee 56
#    When deletes employee 57
    Then the employee 55 is deleted
    And  the employee error message is 404 : [Entity with id 55 not found]
#    Then the employee 2 is deleted
#    And  the error message is 404 : [Entity with id 56 not found]
#    Then the employee 3 is deleted
#    And  the error message is 404 : [Entity with id 57 not found]
