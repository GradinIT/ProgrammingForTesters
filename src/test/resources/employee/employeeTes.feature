Feature: test employee api
  Scenario: client gets all employee
    When the client calls /employee
    Then the client receives 3 employees

  Scenario: client updates first name for employee 1
    When the client updates first name for employee to Johanna
    Then the first name is updated to Johanna

  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the first name is

  Scenario: client updates first name for employee 1
    When the client updates first name for employee to firstName1
    Then the first name is updated to firstName1

  Scenario: client updates last name for employee 1
    When the client updates last name for employee to Salmi
    Then the last name is updated to Salmi

  Scenario: client gets Employee 1
    When the client gets employee 1
    Then the last name is

  Scenario: client updates last name for employee 1
    When the client updates last name for employee to lastName1
    Then the last name is updated to lastName1

Scenario: client deletes employee
  Given the employee
    | 200 | Tobias | Engman | TRUE | 20000 | 1 |
  When the client deletes employee 200
  Then the employee 200 is deleted




