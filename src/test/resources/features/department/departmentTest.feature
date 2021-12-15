Feature: test department api
  Scenario: client gets all departments
    When the client calls /department
    Then the client receives 6 departments
  Scenario: client updates name for department 1
    When the client updates departmentname for department to Coding
    Then the departmentname is updated to Coding
  Scenario: client gets Department 1
    When the client gets department 1
    Then the departmentname is
  Scenario: client updates departmentname for department 1
    When the client updates departmentname for department to Development
    Then the departmentname is updated to Development
  Scenario: client deletes department
    Given the departments
      | 55 | Fun |
      | 56 | HR |
      | 57 | Fika |
    When the client deletes department 55
    When the client deletes department 56
    When the client deletes department 57
    Then the department 55 is deleted
    And  the error message is 404 : ["Entity with id 55 not found"]
    Then the department 56 is deleted
    And  the error message is 404 : ["Entity with id 56 not found"]
    Then the department 57 is deleted
    And  the error message is 404 : ["Entity with id 57 not found"]
