package se.jensen.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Employee {

    private final Integer employeeId;
    private final String firstName;
    private final String lastName;
    private final BigDecimal salary;
    private final Boolean fullTime;

    private Employee(Builder builder) {
        this.employeeId = Objects.requireNonNull(builder.employeeId, "employeeId can't be null");
        this.firstName = Objects.requireNonNull(builder.firstName, "firstName can't be null");
        this.lastName = Objects.requireNonNull(builder.lastName, "lastName can't be null");
        this.salary = Objects.requireNonNull(builder.salary, "salary can't be null");
        this.fullTime = Objects.requireNonNull(builder.fullTime, "fullTime can't be null");
    }

    public Employee() {
        this(new Builder());
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getFullName() {
        return String.format("%s, %s", getFirstName(), getLastName());
    }


    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Boolean getFullTime() {
        return fullTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", fullTime=" + fullTime +
                '}';
    }

    public static class Builder {
        private Integer employeeId;
        private String firstName;
        private String lastName;
        private BigDecimal salary;
        private Boolean fullTime;

        private Builder() {
        }

        public Builder setEmployeeId(Integer employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setSalary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Builder setFullTime(Boolean fullTime) {
            this.fullTime = fullTime;
            return this;
        }

        public Builder of(Employee employee) {
            this.employeeId = employee.employeeId;
            this.firstName = employee.firstName;
            this.lastName = employee.lastName;
            this.salary = employee.salary;
            this.fullTime = employee.fullTime;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
