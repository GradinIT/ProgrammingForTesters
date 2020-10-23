package se.jensen.api;

import se.jensen.entity.Employee;

import java.math.BigDecimal;

public class EmployeeModel {

    private final Integer employeeId;
    private final String firstName;
    private final String lastName;
    private final BigDecimal salary;
    private final Boolean fullTime;

    private EmployeeModel(Builder builder) {
        this.employeeId = builder.employeeId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.salary = builder.salary;
        this.fullTime = builder.fullTime;
    }

    private EmployeeModel() { this(new Builder());}

    public static Builder builder() {
        return new Builder();
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
        return "EmployeeModel{" +
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

        public Builder of(EmployeeModel employeeModel) {
            this.employeeId = employeeModel.employeeId;
            this.firstName = employeeModel.firstName;
            this.lastName = employeeModel.lastName;
            this.salary = employeeModel.salary;
            this.fullTime = employeeModel.fullTime;
            return this;
        }

        public EmployeeModel build() {
            return new EmployeeModel(this);
        }
    }
}
