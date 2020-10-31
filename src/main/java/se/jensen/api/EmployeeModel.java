package se.jensen.api;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.math.BigDecimal;
import java.util.Objects;

@ParametersAreNonnullByDefault
public class EmployeeModel {

    @Nonnull
    private Integer employeeId;
    @Nonnull
    private String firstName;
    @Nonnull
    private String lastName;
    @Nonnull
    private BigDecimal salary;
    @Nonnull
    private Boolean fullTime;

    private EmployeeModel() {
    }

    private EmployeeModel(Builder builder) {
        this.employeeId = builder.employeeId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.salary = builder.salary;
        this.fullTime = builder.fullTime;
    }

    public static Builder builder() {
        return new Builder();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeModel that = (EmployeeModel) o;

        if (!employeeId.equals(that.employeeId)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!salary.equals(that.salary)) return false;
        return fullTime.equals(that.fullTime);
    }

    @Override
    public int hashCode() {
        int result = employeeId.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + salary.hashCode();
        result = 31 * result + fullTime.hashCode();
        return result;
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