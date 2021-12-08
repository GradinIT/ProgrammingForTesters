Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates firstname for employee 1
    When the client updates firstname for employee whit id 1 to Runar
    Then the firstname is updated to Runar
  Scenario: Employee firstname is Runar
    When the client gets employee 1
    Then firstname is Runar
  Scenario: Delete Employee
    Given test data
      | 88 | Socker-Conny | Slagträ | 12500.50 | false | 1 |
      | 89 | Socker-Ronny | Känga | 12500.50 | true | 2 |
      | 90 | Socker-Bonny | Knogjärn | 12500.50 | false | 3 |

