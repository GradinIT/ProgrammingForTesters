Feature: test employee api
    Scenario: client get all employees
        When the client calls all employees
        Then the client receives 6 employee

    Scenario: client get employee by id
        When the client calls employee by id 4
        Then the client receives name Linda