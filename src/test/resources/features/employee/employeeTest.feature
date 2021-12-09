Feature: test employee api
  Scenario: client gets all employees
    When the client calls /employee
    Then the client receives 3 employees
  Scenario: client updates employeeFirstname for employee 1
    When the client updates firstName for employee to Bert
    Then the firstName is updated to Bert