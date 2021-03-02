Feature: test employee api
    Scenario: client get all employees
        When the client calls all employees
        Then the client receives 6 employee

    Scenario: client get employee by id
        When the client calls employee by id 4
        Then the client receives name Linda

    Scenario: client get employees
        Given the employees
            | 10 | Linus | Hellberg | false | 20000.00 | 3 |
        When the client deletes employee with ID 10
        Then the employee 10 is deleted
        And the error message is 404 : [Employee with id 10 not found]