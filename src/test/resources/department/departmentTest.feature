Feature: test department api
  Scenario: client gets all departments
    When D the client calls /department
    Then D the client receives 4 departments
  Scenario: client updates name for department 1
    When D the client updates name for department to Coding
    Then D the name is updated to Coding
  Scenario: client gets Department 1
    When D the client gets department 1
    Then D the name is
  Scenario: client updates name for department 1
    When D the client updates name for department to Development
    Then D the name is updated to Development
  Scenario: client deletes department
    Given D the departments
      | 55 | Fun |
      | 56 | HR |
      | 57 | Fika |
    When D the client deletes department 55
    When D the client deletes department 56
    When D the client deletes department 57
    Then D the department 55 is deleted
    And  D the error message is 404 : [Entity with id 55 not found]
    Then D the department 56 is deleted
    And  D the error message is 404 : [Entity with id 56 not found]
    Then D the department 57 is deleted
    And  D the error message is 404 : [Entity with id 57 not found]
