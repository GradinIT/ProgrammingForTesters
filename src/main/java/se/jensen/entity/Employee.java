package se.jensen.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import se.jensen.util.GenericToStringBuilder;

import java.math.BigDecimal;

@Builder
@EqualsAndHashCode
public class Employee {
    private final @NonNull Integer employeeId;
    private final @NonNull String firstName;
    private final @NonNull String lastName;
    private final @NonNull BigDecimal salary;
    private final @NonNull Boolean fullTime;

    @Override
    public String toString() {
        return GenericToStringBuilder.toString(this);
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
}
