Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates firstname for employee 1 to KlasCoding
    When the client updates firstname for employee to KlasCoding
    Then the firstname is updated to KlasCoding
  Scenario: client updates lastname for employee 1 to DéCoding
    When the client updates lastname for employee to DéCoding
    Then the lastname is updated to DéCoding
  Scenario: client gets employee 1
    When the client gets employee 1
    Then  the firstname is
    And the lastname is
  Scenario: client updates firstname for employee 1
    When the client updates firstname for employee to firstName1
    Then the firstname is updated to firstName1
  Scenario: client updates lastname for employee 1
    When the client updates lastname for employee to lastName1
    Then the lastname is updated to lastName1

