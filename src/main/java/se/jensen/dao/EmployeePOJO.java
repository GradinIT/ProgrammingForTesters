package se.jensen.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity(name = "EMPLOYEE")
public class EmployeePOJO {
    @Id
    private  Integer employeeId;
    private  String firstName;
    private  String lastName;
    private  BigDecimal salary;
    private  Boolean fullTime;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Boolean getFullTime() {
        return fullTime;
    }

    public void setFullTime(Boolean fullTime) {
        this.fullTime = fullTime;
    }
}
