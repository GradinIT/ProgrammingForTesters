Feature: test employee api
    Scenario: client get all employees
        When the client calls all employees
        Then the client receives 6 employees

    Scenario: client creates employee
        When the client creates an employee
        Then the client receives name Anders

    Scenario: client get employee by id
        When the client calls employee by id 4
        Then the client receives employee with name Linda

    Scenario: client get employees
        Given the employees
            | 10 | Linus | Hellberg | false | 20000.00 | 3 |
        When the client deletes employee with ID 10
        Then the employee 10 is deleted
        And the error message is 404 : [Employee with id 10 not found]

    Scenario: client updates employee
        When the client updates firstname to Olle on employee by id 5
        Then name is changed to Olle on employee id 5