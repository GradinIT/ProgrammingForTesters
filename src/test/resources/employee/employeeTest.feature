Feature: test employee api
    Scenario: client gets all employees
        When the client calls /employees
        Then the client receives 3 employees
    Scenario: client updates employee for employee 1
        When now the client updates the employee to Kalle and Carlsson and true and 20000.00 and 3
        Then now the the employee is updated to Kalle and Carlsson and true and 20000.00 and 3
    Scenario: client gets Employee 1
        When now the client gets employees 1
        Then now the name of employee is
        And the size of employee list is checked
    Scenario: client updates employee for employee 1
        When now the client updates the employee to firstName1 and lastname1 and true and 25000.00 and 2
        Then now the the employee is updated to firstName1 and lastname1 and true and 25000.00 and 2
    Scenario: client deletes employee
        Given the employees
           | 40 | Lena | Clark | 10 | 20000.00 | true |
        When the client deletes employee 40
        Then the employee 40 is deleted
        And  it has been an error message 404 : [Entity with id 40 not found]



