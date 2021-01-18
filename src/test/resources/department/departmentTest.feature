Feature: test department api
  Scenario: client gets all departments
    When the client calls /department
    Then the client receives 3 departments
  Scenario: client updates name for department 1
    When the client updates name for department to Coding
    Then the name is updated to Coding
  Scenario: client gets Department 1
    When the client gets department 1
    Then the name is
  Scenario: client updates name for department 1
    When the client updates name for department to Development
    Then the name is updated to Development
  Scenario: client deletes department
    Given the departments
      | 55 | Fun |
    When the client deletes department 55
    Then the department 55 is deleted